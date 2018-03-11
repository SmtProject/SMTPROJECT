package smt.model.tools;

import java.io.Serializable;
import java.util.List;

import com.smt.data.entity.Action;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Year;

public class LoggedInInfo implements Serializable{
	private static final long serialVersionUID = -8358790846009715277L;

	private SmtUser user;
	private Year selectedYear;
	private List<Action> actions;
	
	public LoggedInInfo() {
		
	}
	
	public LoggedInInfo(SmtUser user, Year selectedYear,List<Action> actions) {
		super();
		this.user = user;
		this.selectedYear = selectedYear;
		this.actions=actions;
	}
	
	public SmtUser getUser() {
		return user;
	}
	public void setUser(SmtUser user) {
		this.user = user;
	}
	public Year getSelectedYear() {
		return selectedYear;
	}
	public void setSelectedYear(Year selectedYear) {
		this.selectedYear = selectedYear;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	
	

}
