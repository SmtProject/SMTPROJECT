package com.smt.web.client.teacherView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Teacher;
import com.smt.web.client.service.SmtServiceProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Window;

import smt.model.tools.GradesEnum;

public class TeachingGradesManagementPanel extends Window {

	private static final long serialVersionUID = 1L;

	private TwinColSelect<String> twinColSelect;
	private Teacher teacher;
	private Button saveBtn;
	private Map<GradesEnum, Boolean> gradesToSaveMap = new HashMap<>();

	public TeachingGradesManagementPanel(Teacher teacher) {
		this.teacher = teacher;
		initComponenets();
		initLayout();
		initActionsListener();
	}

	private void initComponenets() {

		twinColSelect = new TwinColSelect<>("add or remove grades");

		List<String> gradesList = Arrays.asList(GradesEnum.values()).stream().map(e -> e.toString())
				.collect(Collectors.toList());

		twinColSelect.setItems(gradesList);

		List<GradesEnum> findTeachingGradesByTeacher = SmtServiceProvider.getInstance().getSmtUserService().findTeachingGradesByTeacher(teacher.getId());
		findTeachingGradesByTeacher.forEach(e -> twinColSelect.select(e.toString()));

		saveBtn = new Button("save");
	}

	private void initLayout() {
		FormLayout mainLayout = new FormLayout();
		mainLayout.addComponent(twinColSelect);
		mainLayout.addComponent(saveBtn);
		setContent(mainLayout);
		mainLayout.addStyleName("mypanelcontent");
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
	}

	private void initActionsListener() {

		saveBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				try {
					for (GradesEnum grade :GradesEnum.values() ) {
						if(twinColSelect.getSelectedItems().contains(grade.toString())) {
							gradesToSaveMap.put(grade, true);
						}else {
							gradesToSaveMap.put(grade, false);
						}
					}
					SmtServiceProvider.getInstance().getSmtUserService().saveTeachingGrades(teacher.getId(),gradesToSaveMap);
				} catch (ValidationException e) {
					e.printStackTrace();
				}
				Notification.show("teacher updated Successfully");
				close();
			}
		});
	}

}
