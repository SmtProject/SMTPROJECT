package com.smt.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.smt.application.service.SmtActionService;
import com.smt.data.entity.Action;
import com.smt.data.entity.QAction;
import com.smt.data.repository.ActionRepository;

import smt.model.tools.ActionEnum;
import smt.model.tools.Role;

public class SmtActionServiceImpl implements SmtActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Action> findActionByRole(Role role) {
		Iterable<Action> actions = actionRepository
				.findAll(QAction.action.smtRole.eq(role).and(QAction.action.isEnable.eq(true)));
		return Lists.newArrayList(actions);

	}

	@Override
	public void updateActions(Map<ActionEnum, Boolean> actions, Role role) {
		actions.forEach((key, value) -> {
			Action action = actionRepository.findOne(QAction.action.smtRole.eq(role).and(QAction.action.name.eq(key)));
			action.setIsEnable(value);
			actionRepository.save(action);
		});

	}

	public void init() {
		Role[] values = Role.values();
		ActionEnum[] actions = ActionEnum.values();
		List<Action> actionList = new ArrayList<>();
		for (Role role : values) {
			for (ActionEnum action : actions) {
				if (actionRepository
						.findOne(QAction.action.name.eq(action).and(QAction.action.smtRole.eq(role))) == null) {
					Action ac = new Action(action, role, false);
					actionList.add(ac);
				}
			}
		}
		actionRepository.save(actionList);
	}

}
