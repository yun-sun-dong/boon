/*
 * Copyright 2013-2014 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * __________                              _____          __   .__
 * \______   \ ____   ____   ____   /\    /     \ _____  |  | _|__| ____    ____
 *  |    |  _//  _ \ /  _ \ /    \  \/   /  \ /  \\__  \ |  |/ /  |/    \  / ___\
 *  |    |   (  <_> |  <_> )   |  \ /\  /    Y    \/ __ \|    <|  |   |  \/ /_/  >
 *  |______  /\____/ \____/|___|  / \/  \____|__  (____  /__|_ \__|___|  /\___  /
 *         \/                   \/              \/     \/     \/       \//_____/
 *      ____.                     ___________   _____    ______________.___.
 *     |    |____ ___  _______    \_   _____/  /  _  \  /   _____/\__  |   |
 *     |    \__  \\  \/ /\__  \    |    __)_  /  /_\  \ \_____  \  /   |   |
 * /\__|    |/ __ \\   /  / __ \_  |        \/    |    \/        \ \____   |
 * \________(____  /\_/  (____  / /_______  /\____|__  /_______  / / ______|
 *               \/           \/          \/         \/        \/  \/
 */

package org.boon.collections;

import org.boon.primitive.Lng;

import java.util.AbstractList;

import static org.boon.primitive.Lng.grow;

/**
 * Created by Richard on 3/16/14.
 */
public class LongList extends AbstractList<Long> {

    /**
     * Values in this list.
     */
    private long [] values;

    /** Index of last value added. */
    private int end;


    /** Create a new list with this many items in it. */
    public LongList(int capacity) {
        this.values = new long[capacity];
    }


    /** Create a new list with exactly 10 items in it. */
    public LongList() {
        this.values = new long[10];
    }

    /** Create a new list with this many items in it. */
    public LongList(long values[]) {
        this.values = values;
        this.end = values.length;
    }


    /** Get the value at index
     * @param index index
     * @return value
     */
    @Override
    public Long get(int index) {
        return values[index];
    }


    /** Get the value at index but don't use a wrapper
     * @param index index
     * @return value
     */
    public final long getInt(int index) {
        return values[index];
    }

    /**
     * Add a new value to the list.
     * @param integer new value
     * @return was able to add.
     */
    @Override
    public boolean add(Long integer) {
        if (end + 1 >= values.length) {
            values = grow(values);
        }
        values [end] = integer;
        end++;
        return true;
    }

    /**
     * Add a new value to the list but don't employ a wrapper.
     * @param integer new value
     * @return was able to add.
     */
    public boolean addLong(long integer) {
        if (end + 1 >= values.length) {
            values = grow(values);
        }
        values [end] = integer;
        end++;
        return true;
    }


    /**
     * Add a new value to the list but don't employ a wrapper.
     * @param integer new value
     * @return was able to add.
     */
    public LongList add(long integer) {
        if (end + 1 >= values.length) {
            values = grow(values);
        }
        values [end] = integer;
        end++;
        return this;
    }


    /**
     * Add a new array to the list.
     * @return was able to add.
     */
    public boolean addArray(long... integers) {
        if (end + integers.length >= values.length) {
            values = grow(values, (values.length + integers.length) * 2);
        }

        System.arraycopy(integers, 0, values, end, integers.length);
        end+=integers.length;
        return true;
    }

    /**
     * Set a value in the list.
     * @param index index
     * @param element new value
     * @return old value at this index
     */
    @Override
    public Long set(int index, Long element) {
        long oldValue = values[index];
        values [index] = element;
        return oldValue;
    }


    /**
     * Set in a new value no wrapper
     * @param index index
     * @param element new value
     * @return old value at this index
     */
    public long setLong(int index, int element) {
        long oldValue = values[index];
        values [index] = element;
        return oldValue;
    }

    /**
     * Return the current size.
     * @return
     */
    @Override
    public int size() {
        return end;
    }



    /** Sums the values with bounds checking. */
    public long sum() {

        return Lng.sum(values, end);
    }


    /**
     * Get a copy of the array up to the end element.
     * @return
     */
    public long [] toValueArray() {

        return java.util.Arrays.copyOfRange(values, 0, end);
    }


    /**
     * This would be a good opportunity to reintroduce dynamic invoke
     * @param function function
     * @return
     */
    public  long reduceBy( Object function ) {
        return Lng.reduceBy(values, end, function);
    }


    /**
     * This would be a good opportunity to reintroduce dynamic invoke
     * @param function function
     * @return
     */
    public  long reduceBy( Object function, String name ) {
        return Lng.reduceBy(values, end, function, name);
    }



    /**
     * @param reduceBy reduceBy function
     * @return the reduction
     */
    public  long reduceBy( Lng.ReduceBy reduceBy ) {
        return Lng.reduceBy(values, end, reduceBy);
    }

    /**
     * Mean
     * @return mean
     */
    public  long mean(  ) {
        return Lng.mean(values, end);
    }


    /**
     * standardDeviation
     * @return standardDeviation
     */
    public  long standardDeviation(  ) {
        return Lng.standardDeviation(values, end);
    }


    /**
     * variance
     * @return variance
     */
    public  long variance(  ) {
        return Lng.variance(values, end);
    }


    /**
     * max
     * @return max
     */
    public  long max(  ) {
        return Lng.max(values, end);
    }


    /**
     * min
     * @return min
     */
    public  long min(  ) {
        return Lng.min(values, end);
    }


    /**
     * median
     * @return median
     */
    public  long median(  ) {
        return Lng.median(values, end);
    }
}