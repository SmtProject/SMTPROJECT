package com.smt.web.client.toolBox;


import java.util.Arrays;

import com.smt.data.entity.SmtUser;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.smt.web.excelImportTable.ExcelImportTableWindow;
import com.smt.web.excelexportTable.ExportType;
import com.smt.web.excelexportTable.SmtComponentHeaderConfiguration;
import com.smt.web.excelexportTable.SmtComponentHeaderConfigurationBuilder;
import com.smt.web.excelexportTable.SmtExportExcelComponentConfiguration;
import com.smt.web.excelexportTable.SmtExportExcelComponentConfigurationBuilder;
import com.smt.web.excelexportTable.SmtExportExcelConfiguration;
import com.smt.web.excelexportTable.SmtExportExcelConfigurationBuilder;
import com.smt.web.excelexportTable.SmtExportExcelSheetConfiguration;
import com.smt.web.excelexportTable.SmtExportExcelSheetConfigurationBuilder;
import com.smt.web.excelexportTable.SmtExportToExcel;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;


public class BtnFactory {
	
	public static MenuBar ExportGridBtn(Grid<?> userGrid,TableName tableName,ColumnsType columnsType) {
		MenuBar	export = new MenuBar();
		MenuItem exportmenu=export.addItem("Export",VaadinIcons.FILE,null);		
		MenuItem exportExcel=exportmenu.addItem("Export Excel", new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				SmtExportToExcel<SmtUser> exportToExcelUtility = customizeExportExcelUtility(ExportType.XLSX, userGrid);
				exportToExcelUtility.export();

			}
		});
		exportExcel.setIcon(VaadinIcons.FILE);
		MenuItem exportCsv=exportmenu.addItem("Export CSV", new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				SmtExportToExcel<SmtUser> exportToExcelUtility = customizeExportExcelUtility(ExportType.CSV, userGrid);
				exportToExcelUtility.export();
			}
		});
		exportCsv.setIcon(VaadinIcons.FILE);
		return export;
	}
	public static Button createSaveBtn() {
		Button saveBtn=new Button("Save");
		saveBtn.setIcon(VaadinIcons.SAFE);
		saveBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		saveBtn.setClickShortcut(KeyCode.ENTER);
		return saveBtn;
		
	}
	public static Button createImportBtn(ImportState importState) {
		Button importExcelButton=new Button("Import Excel");
		importExcelButton.setIcon(VaadinIcons.FILE);
		importExcelButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				ExcelImportTableWindow excelimporttableApplication = new ExcelImportTableWindow(importState);
				UI.getCurrent().addWindow(excelimporttableApplication);
				excelimporttableApplication.center();
			}
		});

		return importExcelButton;
	}
	@SuppressWarnings("unchecked")
	public static SmtExportToExcel<SmtUser> customizeExportExcelUtility(ExportType exportType,Grid<?> grid) {

		String[] array = new String[grid.getColumns().size()];
		for (int i = 0; i < grid.getColumns().size(); i++) {
			array[i] = grid.getColumns().get(i).getCaption();
		}
		
		SmtComponentHeaderConfiguration build = new SmtComponentHeaderConfigurationBuilder().withAutoFilter(true)
			.withColumnKeys(array)
			.build();
		SmtExportExcelComponentConfiguration<SmtUser> componentConfig1 = new SmtExportExcelComponentConfigurationBuilder()
			.withGrid(grid)
			.withVisibleProperties(array)
			.withHeaderConfigs(Arrays.asList(build))
			.withIntegerFormattingProperties(Arrays.asList("counter"))
			.withFloatFormattingProperties(Arrays.asList("totalCosts", "differenceToMin"))
			.withBooleanFormattingProperties(Arrays.asList("active"))
			.build();

		
		/* Configuring Sheets */
		SmtExportExcelSheetConfiguration<SmtUser> sheetConfig1 = new SmtExportExcelSheetConfigurationBuilder()
			.withComponentConfigs(Arrays.asList(componentConfig1))
			.withIsHeaderSectionRequired(Boolean.TRUE)
			.build();


		/* Configuring Excel */
		SmtExportExcelConfiguration<SmtUser> config1 = new SmtExportExcelConfigurationBuilder()
			.withSheetConfigs(Arrays.asList(sheetConfig1))
			.build();

		return new SmtExportToExcel<SmtUser>(exportType, config1);
	}

}
