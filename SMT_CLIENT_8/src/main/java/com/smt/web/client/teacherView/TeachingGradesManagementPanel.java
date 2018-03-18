package com.smt.web.client.teacherView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Teacher;
import com.smt.data.entity.TeachingGrades;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;

import smt.model.tools.ClassesEnum;
import smt.model.tools.GradesEnum;

public class TeachingGradesManagementPanel extends Window {

	private static final long serialVersionUID = 1L;

	private ComboBox<String> gradeComboBox;
	private ComboBox<String> classesComboBox;
	private Teacher teacher;
	private Button saveBtn;
	private Button addToTable;
	private Grid<TeachingGrades> table;
	private Map<String, List<String>> classesGradesMap = new HashMap<>();
	protected List<TeachingGrades> data;

	public TeachingGradesManagementPanel(Teacher teacher) {
		this.teacher = teacher;
		initComponenets();
		initLayout();
		initActionsListener();
	}

	private void initComponenets() {
		data = SmtServiceProvider.getInstance().getSmtUserService().findTeachingGradesByTeacher(teacher.getId());
		table = new Grid<>(TeachingGrades.class);
		table.getColumn("teacherId").setHidden(true);
		table.getColumn("id").setHidden(true);
		table.setItems(data);
		saveBtn = BtnFactory.createSaveBtn();
		addToTable = new Button("Assign");

		gradeComboBox = new ComboBox<>();
		classesComboBox = new ComboBox<>();

		List<String> gradesList = Arrays.asList(GradesEnum.values()).stream().map(e -> e.toString())
				.collect(Collectors.toList());

		List<String> classesList = Arrays.asList(ClassesEnum.values()).stream().map(e -> e.toString())
				.collect(Collectors.toList());

		gradesList.forEach(e -> classesGradesMap.put(e, new ArrayList<>(classesList)));

		Map<GradesEnum, List<TeachingGrades>> gradesSet = data.stream()
				.collect(Collectors.groupingBy(TeachingGrades::getGrade));

		gradesSet.forEach((key, value) -> {
			List<String> listOfClasses = classesGradesMap.get(key.toString());
			value.forEach(teachingClass -> listOfClasses.remove(teachingClass.getTeachingClass().toString()));

		});

		gradeComboBox.setItems(gradesList);

		gradeComboBox.setSelectedItem(gradesList.get(0));

		String gradeSelectedItem = gradeComboBox.getSelectedItem().get();

		List<String> items = classesGradesMap.get(gradeSelectedItem);

		classesComboBox.setItems(items);

		classesComboBox.setSelectedItem(items.get(0));

	}

	private void initLayout() {
		FormLayout mainLayout = new FormLayout();
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		horizontalLayout.addComponent(gradeComboBox);
		horizontalLayout.addComponent(classesComboBox);

		mainLayout.addComponent(horizontalLayout);
		mainLayout.addComponent(table);
		mainLayout.addComponent(addToTable);
		mainLayout.addComponent(saveBtn);

		setContent(mainLayout);
		mainLayout.addStyleName("mypanelcontent");
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
	}

	private void initActionsListener() {

		gradeComboBox.addValueChangeListener(new ValueChangeListener<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent<String> event) {
				classesComboBox.clear();
				String gradeSelectedItem = gradeComboBox.getSelectedItem().get();
				List<String> classesList = classesGradesMap.get(gradeSelectedItem);
				classesComboBox.setItems(classesList);
				classesComboBox.setSelectedItem(classesList.get(0));
			}
		});

		addToTable.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				String gradeSelectedItem = gradeComboBox.getSelectedItem().get();
				String classSelectedItem = classesComboBox.getSelectedItem().get();
				TeachingGrades teachingGrades = new TeachingGrades(teacher.getId(),
						GradesEnum.fromString(gradeSelectedItem), ClassesEnum.fromString(classSelectedItem));
				data.add(teachingGrades);
				table.getDataProvider().refreshAll();

				List<String> classesList = classesGradesMap.get(gradeSelectedItem);
				classesList.remove(classSelectedItem);
				classesComboBox.clear();
				classesComboBox.setItems(classesList);
				classesComboBox.setSelectedItem(classesList.get(0));

			}
		});
		saveBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				try {
					SmtServiceProvider.getInstance().getSmtUserService().saveTeachingGrades(teacher.getId(), data);
					Notification.show("Saved Successfully");
				} catch (ValidationException e) {
					e.printStackTrace();
				}

			}
		});

	}

}
