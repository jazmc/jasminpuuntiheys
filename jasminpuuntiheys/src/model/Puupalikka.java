package model;

public class Puupalikka {
	// paikalliset muuttujat
	private long id;
	private double tiheys;
	private double korkeus;
	private double leveys;
	private double paino;
	private double pituus;
	private String grain;

	@SuppressWarnings("unused")
	private Puupalikka() {
	}

	// puupalikkaolio id:n kanssa
	public Puupalikka(long id, double tiheys, double korkeus, double leveys, double paino, double pituus,
			String grain) {
		this.id = id;
		this.tiheys = tiheys;
		this.korkeus = korkeus;
		this.leveys = leveys;
		this.paino = paino;
		this.pituus = pituus;
		this.grain = grain;
	}

	// puupalikkaolio ilman id:tä
	public Puupalikka(double tiheys, double korkeus, double leveys, double paino, double pituus, String grain) {
		this.tiheys = tiheys;
		this.korkeus = korkeus;
		this.leveys = leveys;
		this.paino = paino;
		this.pituus = pituus;
		this.grain = grain;
	}

	// getterit kaikille ja setteri id:lle
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTiheys() {
		return tiheys;
	}

	public double getKorkeus() {
		return korkeus;
	}

	public double getLeveys() {
		return leveys;
	}

	public double getPaino() {
		return paino;
	}

	public double getPituus() {
		return pituus;
	}

	public String getGrain() {
		return grain;
	}

	// muotoilumetodi stringeille
	public static double muotoileDouble(String muotoiltava) {
		double muotoiltu = Double.parseDouble(muotoiltava.replace(',', '.'));
		return muotoiltu;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Puupalikka && ((Puupalikka) other).id == this.id;
	}

}