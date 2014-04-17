package antennaeter.antenna;

/*
 * Represents a single antenna object.
 */
public class Antenna {
	public enum antennaType {
		YAGI, DIPOLE, MONOPOLE, LOOP
	}

	private double m_latitude;
	private double m_longitude;
	private double m_heading; // Direction antenna points, in degrees. North is 0
						// degrees, angles increase clockwise, so, e.g. East is
						// 90 degrees.
	private antennaType m_antennaType;
	private String m_comments;// Optional field that gives any comments about the
						// antenna.

	public Antenna(double latitude, double longitude, double heading,
			antennaType antennaType, String comments) {
		m_latitude = latitude;
		m_longitude = longitude;
		m_heading = heading;
		m_antennaType = antennaType;
		m_comments = comments;
	}

	public Antenna(double latitude, double longitude, double heading,
			antennaType antennaType) {
		this(latitude, longitude, heading, antennaType, null);
	}
	public double getLatitude(){
		return m_latitude;
	}
	public double getLongitude(){
		return m_longitude;
	}
	public double getHeading(){
		return m_heading;
	}
	public antennaType getAntennaType(){
		return m_antennaType;
	}
	public String getComments(){
		return m_comments;
	}

}
