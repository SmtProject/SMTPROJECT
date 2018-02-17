package com.smt.web.client.toolBox;

import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.addon.tableexport.CsvExport;
import com.vaadin.addon.tableexport.DefaultTableHolder;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;


public class BtnFactory {
	
	public static MenuBar ExportGridBtn(Grid grid,TableName tableName,ColumnsType columnsType) {
		MenuBar	export = new MenuBar();
		MenuItem exportmenu=export.addItem("Export", FontAwesome.FILE_EXCEL_O,null);		
		MenuItem exportExcel=exportmenu.addItem("Export Excel", new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Table table = new Table("", grid.getContainerDataSource());
				table.setVisibleColumns(TableColumnFactory.getTableColumn(tableName,columnsType));
				ExcelExport excelExport = new ExcelExport(new DefaultTableHolder(table));
				excelExport.excludeCollapsedColumns();
				excelExport.setDisplayTotals(false);
				excelExport.export();
			}
		});
		exportExcel.setIcon(FontAwesome.FILE_EXCEL_O);
		MenuItem exportCsv=exportmenu.addItem("Export CSV", new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Table table = new Table("", grid.getContainerDataSource());
				CsvExport csvExport = new CsvExport(new DefaultTableHolder(table));
				csvExport.excludeCollapsedColumns();
				csvExport.setDisplayTotals(false);
				csvExport.export();
			}
		});
		exportCsv.setIcon(FontAwesome.FILE);
		return export;
	}
	public static Button createSaveBtn() {
		Button saveBtn=new Button("Save");
		saveBtn.setIcon(FontAwesome.SAVE);
		saveBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		saveBtn.setClickShortcut(KeyCode.ENTER);
		return saveBtn;
		
	}

}
