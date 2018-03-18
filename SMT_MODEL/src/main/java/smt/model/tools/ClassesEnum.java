package smt.model.tools;

public enum ClassesEnum {
	ARABIC, FRENCH, MATH, PHYSICS, CHEMISTRY, BIOLOGIE, HISTORY, GEOGRAPHY, CIVICـEDUCATION, ECONOMIC, SOCIALـSCIENCES, PHILOSOPHY, ENGLISH, SPORT, COMPUTER, DRAWING;

	@Override
	public String toString() {
		switch (this) {
		case ARABIC:
			return "Arabic";
		case FRENCH:
			return "French";
		case MATH:
			return "Math";
		case PHYSICS:
			return "Physics";
		case CHEMISTRY:
			return "Chemistry";
		case BIOLOGIE:
			return "Biologie";
		case HISTORY:
			return "History";
		case GEOGRAPHY:
			return "Geography";
		case CIVICـEDUCATION:
			return "Civil Education";
		case ECONOMIC:
			return "Economic";
		case SOCIALـSCIENCES:
			return "Socail Sciences";
		case PHILOSOPHY:
			return "Philosophy";
		case ENGLISH:
			return "English";
		case SPORT:
			return "Sport";
		case COMPUTER:
			return "Computer";
		case DRAWING:
			return "Drawing";
		default:
			return "";
		}

	}

	public static ClassesEnum fromString(String st) {
		if (st.equals("Arabic"))
			return ARABIC;
		if (st.equals("French"))
			return FRENCH;
		if (st.equals("Math"))
			return MATH;
		if (st.equals("Physics"))
			return PHYSICS;
		if (st.equals("Chemistry"))
			return CHEMISTRY;
		if (st.equals("biologie"))
			return BIOLOGIE;
		if (st.equals("history"))
			return HISTORY;
		if (st.equals("Geography"))
			return GEOGRAPHY;
		if (st.equals("Civil Education"))
			return CIVICـEDUCATION;
		if (st.equals("Economic"))
			return ECONOMIC;
		if (st.equals("Social Sciences"))
			return SOCIALـSCIENCES;
		if (st.equals("Phylosophy"))
			return PHILOSOPHY;
		if (st.equals("English"))
			return ENGLISH;
		if (st.equals("Sport"))
			return SPORT;
		if (st.equals("Computer"))
			return COMPUTER;
		if (st.equals("Drawing"))
			return DRAWING;
		return null;
	}

}
