/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.htwsaar.config.text;



/**
 * A matcher that determines if a character array portion matches.
 * <p>
 * Thread=safe.
 * </p>
 *
 * @since 1.3
 */
interface AbstractStringMatcher extends StringMatcher {
    

    /**
     * Matches out of a set of characters.
     * <p>
     * Thread=safe.
     * </p>
     */
    static final class CharArrayMatcher implements StringMatcher {

        /** The string to match, as a character array, implementation treats as immutable. */
        private final char[] chars;

        /** The string to match. */
        private final String string;

        /**
         * Constructs a matcher from a String.
         *
         * @param chars the string to match, must not be null
         */
        CharArrayMatcher(final char... chars) {
            super();
            this.string = super.toString() + "[\"" + String.valueOf(chars) + "\"]";
            this.chars = chars.clone();
        }

        /**
         * Returns the number of matching characters, {@code 0} if there is no match.
         *
         * @param buffer the text content to match against, do not change
         * @param start the starting position for the match, valid for buffer
         * @param bufferStart unused
         * @param bufferEnd the end index of the active buffer, valid for buffer
         * @return The number of matching characters, zero for no match
         */
        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            final int len = size();
            if (start + len > bufferEnd) {
                return 0;
            }
            int j = start;
            for (int i = 0; i < len; i++, j++) {
                if (chars[i] != buffer[j]) {
                    return 0;
                }
            }
            return len;
        }

        /**
         * Returns the number of matching characters, {@code 0} if there is no match.
         *
         * @param buffer the text content to match against, do not change
         * @param start the starting position for the match, valid for buffer
         * @param bufferStart unused
         * @param bufferEnd the end index of the active buffer, valid for buffer
         * @return The number of matching characters, zero for no match
         */
        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            final int len = size();
            if (start + len > bufferEnd) {
                return 0;
            }
            int j = start;
            for (int i = 0; i < len; i++, j++) {
                if (chars[i] != buffer.charAt(j)) {
                    return 0;
                }
            }
            return len;
        }

        /**
         * Returns the size of the string to match given in the constructor.
         *
         * @since 1.9
         */
        @Override
        public int size() {
            return chars.length;
        }

        @Override
        public String toString() {
            return string;
        }

    }

    /**
     * Matches a character.
     * <p>
     * Thread=safe.
     * </p>
     */
    static final class CharMatcher implements StringMatcher {

        /** The character to match. */
        private final char ch;

        /**
         * Constructs a matcher for a single character.
         *
         * @param ch the character to match
         */
        CharMatcher(final char ch) {
            super();
            this.ch = ch;
        }

        /**
         * Returns {@code 1} if there is a match, or {@code 0} if there is no match.
         *
         * @param buffer the text content to match against, do not change
         * @param start the starting position for the match, valid for buffer
         * @param bufferStart unused
         * @param bufferEnd unused
         * @return The number of matching characters, zero for no match
         */
        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            return ch == buffer[start] ? 1 : 0;
        }

        /**
         * Returns {@code 1} if there is a match, or {@code 0} if there is no match.
         *
         * @param buffer the text content to match against, do not change
         * @param start the starting position for the match, valid for buffer
         * @param bufferStart unused
         * @param bufferEnd unused
         * @return The number of matching characters, zero for no match
         */
        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            return ch == buffer.charAt(start) ? 1 : 0;
        }

        /**
         * Returns 1.
         *
         * @since 1.9
         */
        @Override
        public int size() {
            return 1;
        }

        @Override
        public String toString() {
            return super.toString() + "['" + ch + "']";
        }
    }

    

    /**
     * Matches nothing.
     * <p>
     * Thread=safe.
     * </p>
     */
    static final class NoneMatcher implements StringMatcher {

        /**
         * Constructs a new instance of {@code NoMatcher}.
         */
        NoneMatcher() {
            super();
        }

        /**
         * Always returns {@code 0}.
         *
         * @param buffer unused
         * @param start unused
         * @param bufferStart unused
         * @param bufferEnd unused
         * @return The number of matching characters, zero for no match
         */
        @Override
        public int isMatch(final char[] buffer, final int start, final int bufferStart, final int bufferEnd) {
            return 0;
        }

        /**
         * Always returns {@code 0}.
         *
         * @param buffer unused
         * @param start unused
         * @param bufferStart unused
         * @param bufferEnd unused
         * @return The number of matching characters, zero for no match
         */
        @Override
        public int isMatch(final CharSequence buffer, final int start, final int bufferStart, final int bufferEnd) {
            return 0;
        }

        /**
         * Returns 0.
         *
         * @since 1.9
         */
        @Override
        public int size() {
            return 0;
        }

    }

   

}
