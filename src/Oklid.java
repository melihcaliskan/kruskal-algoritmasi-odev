import java.util.Collections;

public class Oklid implements Comparable<Oklid> {
	public int x1, x2, y1, y2;

	public Oklid(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public double hesapla() {
		return Math.sqrt((Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
	}

	@Override
	public int compareTo(Oklid o) {
		return Double.compare(this.hesapla(), o.hesapla());
	}

	public String toString() {
		return String.valueOf(hesapla());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Oklid) {
			Oklid temp = (Oklid) obj;
			if (this.x1 == temp.x1 && this.x2 == temp.x2 && this.y1 == temp.y1 && this.y2 == temp.y2)
				return true;
		}
		return false;

	}
}