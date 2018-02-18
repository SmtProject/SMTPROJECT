package com.smt.application.service;

import java.util.List;
import java.util.Map;

import com.smt.data.entity.Action;

import smt.model.tools.ActionEnum;
import smt.model.tools.Role;

public interface SmtActionService {

	List<Action> findActionByRole(Role role);

	void updateActions(Map<ActionEnum,Boolean> actions, Role role) ;

}
