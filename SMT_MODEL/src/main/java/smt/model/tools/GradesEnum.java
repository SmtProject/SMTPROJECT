package smt.model.tools;

public enum GradesEnum {
	KG, KG_1, KG_2, GRADE_1, GRADE_2, GRADE_3, GRADE_4, GRADE_5, GRADE_6, GRADE_7, GRADE_8, GRADE_9, GRADE_10, GRADE_11, GRADE_12;

	@Override
	public String toString() {
		switch (this) {
		case KG:
			return "KG";
		case KG_1:
			return "KG1";
		case KG_2:
			return "KG2";
		case GRADE_1:
			return "Grade 1";
		case GRADE_2:
			return "Grade 2";
		case GRADE_3:
			return "Grade 3";
		case GRADE_4:
			return "Grade 4";
		case GRADE_5:
			return "Grade 5";
		case GRADE_6:
			return "Grade 6";
		case GRADE_7:
			return "Grade 7";
		case GRADE_8:
			return "Grade 8";
		case GRADE_9:
			return "Grade 9";
		case GRADE_10:
			return "Grade 10";
		case GRADE_11:
			return "Grade 11";
		case GRADE_12:
			return "Grade 12";
		default:
			return "";
		}

	}

	public static GradesEnum fromString(String st) {
		if (st.equals("KG"))
			return KG;
		if (st.equals("KG1"))
			return KG_1;
		if (st.equals("KG2"))
			return KG_2;
		if (st.equals("Grade 1"))
			return GRADE_1;
		if (st.equals("Grade 2"))
			return GRADE_2;
		if (st.equals("Grade 3"))
			return GRADE_3;
		if (st.equals("Grade 4"))
			return GRADE_4;
		if (st.equals("Grade 5"))
			return GRADE_5;
		if (st.equals("Grade 6"))
			return GRADE_6;
		if (st.equals("Grade 7"))
			return GRADE_7;
		if (st.equals("Grade 8"))
			return GRADE_8;
		if (st.equals("Grade 9"))
			return GRADE_9;
		if (st.equals("Grade 10"))
			return GRADE_10;
		if (st.equals("Grade 11"))
			return GRADE_11;
		if (st.equals("Grade 12"))
			return GRADE_12;
		return null;
	}

}
