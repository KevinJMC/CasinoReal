package com.casinoreal;

/**
 * Created by kevinmccann on 1/24/17.
 */
public enum Suit {
    SPADE {
        public String toString() {
            return "\u2660";
        }
    },
    HEART {
        public String toString() {
            return "\u2665";
        }
    },
    DIAMOND{
        public String toString() {
            return "\u2666";
        }
    },
    CLUB{
        public String toString() {
            return "\u2663";
        }
    }
}
