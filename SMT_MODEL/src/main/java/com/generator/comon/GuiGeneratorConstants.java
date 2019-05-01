package com.generator.comon;


public class GuiGeneratorConstants {
	
	public static String CLASS_NAME="className";
	public static String ENTITY="entity";
	public static String GRID_COLUMN="gridColumns";
	public static String GRIDS_CLASSES="gridsclasses";

	
	public static String GRID_CLASS="package com.gui;\n" + 
			"\n" + 
			"import com.exception.CustomException;\n" + 
			"import com.model.${"+CLASS_NAME+"};\n" + 
			"import com.service.Services;\n" + 
			"import com.vaadin.ui.Grid;\n" + 
			"\n" + 
			"public class ${"+CLASS_NAME+"}Grid extends Grid<${"+CLASS_NAME+"}>{\n" + 
			"	private static final long serialVersionUID = 9202728836701096130L;\n" + 
			"\n" + 
			"	public ${"+CLASS_NAME+"}Grid() throws CustomException {\n" + 
			"		super(\"${"+CLASS_NAME+"}\", Services.getinstance().get${"+CLASS_NAME+"}Service().getAll${"+CLASS_NAME+"}());\n" + 
			"		this.setSizeFull();\n" + 
			"${"+GRID_COLUMN+"}" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";
	public static String COLUMN="this.addColumn(${"+CLASS_NAME+"}::get${"+ENTITY+"}).setCaption(\"${"+ENTITY+"}\");\n";
	
	public static String MAIN_GUI_CLASS="package com.gui;\n" + 
			"\n" + 
			"\n" + 
			"import com.service.Services;\n" + 
			"import com.vaadin.ui.Label;\n" + 
			"import com.vaadin.ui.TabSheet;\n" + 
			"import com.vaadin.ui.VerticalLayout;\n" + 
			"\n" + 
			"public class MainGui extends VerticalLayout{\n" + 
			"	\n" + 
			"	private TabSheet tabsheet = new TabSheet();\n" + 
			"\n" + 
			"	public MainGui() {\n" + 
			"		initLayout();\n" + 
			"		addTabs();\n" + 
			"	}\n" + 
			"\n" + 
			"	private void initLayout() {\n" + 
			"		this.addComponent(tabsheet);\n" + 
			"	}\n" + 
			"	private void addTabs() {\n" + 
			"\n try{ \n"
			+ "${"+GRIDS_CLASSES+"}" + 
			"\n" + 
			"}catch(Exception e ){System.out.print(e.getMessage());}	}\n" + 
			"\n" + 
			"}\n" + 
			"";

}
