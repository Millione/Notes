class Pair {
    int pos;
    int val;
    public Pair(int pos, int val) {
        this.pos = pos;
        this.val = val;
    }  
}

class Tuple {
    int x;
    int y;
    int val;
    public Tuple(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }  
}

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return  x == pair.x && y == pair.y;
    }

    public int hashCode(){
        return this.x * 31 + this.y;
    }
}