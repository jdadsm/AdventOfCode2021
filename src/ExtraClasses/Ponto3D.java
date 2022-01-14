package ExtraClasses;

public class Ponto3D {
    private int x;
    private int y;
    private int z;

    public Ponto3D() {
        x = 0;
        y = 0;
        z = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public Ponto3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Ponto3D add(Ponto3D p) {
        return new Ponto3D(this.x + p.getX(), this.y + p.getY(), this.z + p.getZ());
    }

    public Ponto3D sub(Ponto3D p) {
        return new Ponto3D(this.x - p.getX(), this.y - p.getY(), this.z - p.getZ());
    }

    public boolean equals(Ponto3D p) {
        if (this.x == p.getX() && this.y == p.getY() && this.z == p.getZ()) {
            return true;
        }
        return false;
    }

    public Ponto3D differentOrientationsSub(Ponto3D p, int o) {
        switch (o) {
            case 1:
                return new Ponto3D(this.x - p.getX(), this.y - p.getY(), this.z - p.getZ());
            case 2:
                return new Ponto3D(this.x - p.getX(), this.y - p.getZ(), this.z + p.getY());
            case 3:
                return new Ponto3D(this.x - p.getX(), this.y + p.getY(), this.z + p.getZ());
            case 4:
                return new Ponto3D(this.x - p.getX(), this.y + p.getZ(), this.z - p.getY());

            case 5:
                return new Ponto3D(this.x - p.getY(), this.y + p.getX(), this.z - p.getZ());
            case 6:
                return new Ponto3D(this.x - p.getY(), this.y - p.getZ(), this.z - p.getX());
            case 7:
                return new Ponto3D(this.x - p.getY(), this.y - p.getX(), this.z + p.getZ());
            case 8:
                return new Ponto3D(this.x - p.getY(), this.y + p.getZ(), this.z + p.getX());

            case 9:
                return new Ponto3D(this.x + p.getX(), this.y + p.getY(), this.z - p.getZ());
            case 10:
                return new Ponto3D(this.x + p.getX(), this.y - p.getZ(), this.z - p.getY());
            case 11:
                return new Ponto3D(this.x + p.getX(), this.y - p.getY(), this.z + p.getZ());
            case 12:
                return new Ponto3D(this.x + p.getX(), this.y + p.getZ(), this.z + p.getY());

            case 13:
                return new Ponto3D(this.x + p.getY(), this.y - p.getX(), this.z - p.getZ());
            case 14:
                return new Ponto3D(this.x + p.getY(), this.y - p.getZ(), this.z + p.getX());
            case 15:
                return new Ponto3D(this.x + p.getY(), this.y + p.getX(), this.z + p.getZ());
            case 16:
                return new Ponto3D(this.x + p.getY(), this.y + p.getZ(), this.z - p.getX());

            case 17:
                return new Ponto3D(this.x - p.getZ(), this.y - p.getY(), this.z + p.getX());
            case 18:
                return new Ponto3D(this.x - p.getZ(), this.y + p.getX(), this.z + p.getY());
            case 19:
                return new Ponto3D(this.x - p.getZ(), this.y + p.getY(), this.z - p.getX());
            case 20:
                return new Ponto3D(this.x - p.getZ(), this.y - p.getX(), this.z - p.getY());

            case 21:
                return new Ponto3D(this.x + p.getZ(), this.y - p.getX(), this.z + p.getY());
            case 22:
                return new Ponto3D(this.x + p.getZ(), this.y + p.getY(), this.z + p.getX());
            case 23:
                return new Ponto3D(this.x + p.getZ(), this.y + p.getX(), this.z - p.getY());
            case 24:
                return new Ponto3D(this.x + p.getZ(), this.y - p.getY(), this.z - p.getX());
            default:
                break;
        }
        return null;
    }

    public Ponto3D differentOrientationsAdd(Ponto3D p, int o) {
        switch (o) {
            case 0:
                return p;
            case 1:
                return new Ponto3D(this.x + p.getX(), this.y + p.getY(), this.z + p.getZ());
            case 2:
                return new Ponto3D(this.x + p.getX(), this.y + p.getZ(), this.z - p.getY());
            case 3:
                return new Ponto3D(this.x + p.getX(), this.y - p.getY(), this.z - p.getZ());
            case 4:
                return new Ponto3D(this.x + p.getX(), this.y - p.getZ(), this.z + p.getY());

            case 5:
                return new Ponto3D(this.x + p.getY(), this.y - p.getX(), this.z + p.getZ());
            case 6:
                return new Ponto3D(this.x + p.getY(), this.y + p.getZ(), this.z + p.getX());
            case 7:
                return new Ponto3D(this.x + p.getY(), this.y + p.getX(), this.z - p.getZ());
            case 8:
                return new Ponto3D(this.x + p.getY(), this.y - p.getZ(), this.z - p.getX());

            case 9:
                return new Ponto3D(this.x - p.getX(), this.y - p.getY(), this.z + p.getZ());
            case 10:
                return new Ponto3D(this.x - p.getX(), this.y + p.getZ(), this.z + p.getY());
            case 11:
                return new Ponto3D(this.x - p.getX(), this.y + p.getY(), this.z - p.getZ());
            case 12:
                return new Ponto3D(this.x - p.getX(), this.y - p.getZ(), this.z - p.getY());

            case 13:
                return new Ponto3D(this.x - p.getY(), this.y + p.getX(), this.z + p.getZ());
            case 14:
                return new Ponto3D(this.x - p.getY(), this.y + p.getZ(), this.z - p.getX());
            case 15:
                return new Ponto3D(this.x - p.getY(), this.y - p.getX(), this.z - p.getZ());
            case 16:
                return new Ponto3D(this.x - p.getY(), this.y - p.getZ(), this.z + p.getX());

            case 17:
                return new Ponto3D(this.x + p.getZ(), this.y + p.getY(), this.z - p.getX());
            case 18:
                return new Ponto3D(this.x + p.getZ(), this.y - p.getX(), this.z - p.getY());
            case 19:
                return new Ponto3D(this.x + p.getZ(), this.y - p.getY(), this.z + p.getX());
            case 20:
                return new Ponto3D(this.x + p.getZ(), this.y + p.getX(), this.z + p.getY());

            case 21:
                return new Ponto3D(this.x - p.getZ(), this.y + p.getX(), this.z - p.getY());
            case 22:
                return new Ponto3D(this.x - p.getZ(), this.y - p.getY(), this.z - p.getX());
            case 23:
                return new Ponto3D(this.x - p.getZ(), this.y - p.getX(), this.z + p.getY());
            case 24:
                return new Ponto3D(this.x - p.getZ(), this.y + p.getY(), this.z + p.getX());
            default:
                break;
        }
        return null;
    }

    public Ponto3D customOrientationAdd(Ponto3D p, Character[] position, int[] sinal) {

        if (position[0] == 'x') {
            if (position[1] == 'y') {
                return new Ponto3D(this.x + p.x * sinal[0], this.y + p.y * sinal[1], this.z + p.z * sinal[2]);
            } else if (position[2] == 'y') {
                return new Ponto3D(this.x + p.x * sinal[0], this.y + p.z * sinal[1], this.z + p.y * sinal[2]);
            }
        } else if (position[1] == 'x') {
            if (position[0] == 'y') {
                return new Ponto3D(this.x + p.y * sinal[0], this.y + p.x * sinal[1], this.z + p.z * sinal[2]);
            } else if (position[2] == 'y') {
                return new Ponto3D(this.x + p.z * sinal[0], this.y + p.x * sinal[1], this.z + p.y * sinal[2]);
            }
        } else if (position[2] == 'x') {
            if (position[0] == 'y') {
                return new Ponto3D(this.x + p.y * sinal[0], this.y + p.z * sinal[1], this.z + p.x * sinal[2]);
            } else if (position[1] == 'y') {
                return new Ponto3D(this.x + p.z * sinal[0], this.y + p.y * sinal[1], this.z + p.x * sinal[2]);
            }
        }

        return null;
    }
}