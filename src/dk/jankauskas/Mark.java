package dk.jankauskas;

/**
 * Created by martynasjankauskas on 20/10/15.
 */
public enum Mark {
    EMPTY {
        @Override
        public String toString() {
            return "";
        }
    }, X {
        @Override
        public String toString() {
            return "X";
        }
    }, O {
        @Override
        public String toString() {
            return "O";
        }
    }
}
