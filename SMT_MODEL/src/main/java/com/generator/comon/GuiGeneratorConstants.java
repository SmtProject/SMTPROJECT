package com.generator.comon;


public class GuiGeneratorConstants {
	
	public static String CLASS_NAME="className";
	public static String ENTITY="entity";
	public static String GRID_COLUMN="gridColumns";
	public static String GRIDS_CLASSES="gridsclasses";
	public static String COMPONENTS="Components";
	public static String BINDINGS="Bindings";
	public static String ADD_COMPONENTS="addComponents";
	public static String SET_CAPTIONS="setCaptions";
	public static String CUS_MANY_TO_ONE="CUS_MANY_TO_ONE";


	public static String GRID_CLASS="package com.gui;\n" + 
	"\n" + 
	"import java.util.ArrayList;\n" + 
	"import java.util.Collection;\n" + 
	"import java.util.List;\n" + 
	"\n" + 
	"import com.exception.CustomException;\n" + 
	"import com.model.${"+CLASS_NAME+"};\n" + 
	"import com.service.Services;\n" + 
	"\n" + 
	"public class ${"+CLASS_NAME+"}Grid extends FilteredGrid<${"+CLASS_NAME+"}>{\n" + 
	"	private static final long serialVersionUID = 9202728836701096130L;\n" + 
	"\n" + 
	"	public ${"+CLASS_NAME+"}Grid()  {\n" + 
	"		super(\"${"+CLASS_NAME+"}\", new ArrayList<>());\n" + 
	"		this.setSizeFull();\n" + 
	"        ${"+GRID_COLUMN+"}\n" + 
	"		initFilter();\n" + 
	"		refreshData();\n" + 
	"	}\n" + 
	"\n" + 
	"	@Override\n" + 
	"	public Collection<${"+CLASS_NAME+"}> refreshData() {\n" + 
	"		try {\n" + 
	"			List<${"+CLASS_NAME+"}> objects = Services.getinstance().get${"+CLASS_NAME+"}Service().getAll${"+CLASS_NAME+"}();\n" + 
	"			this.setItems(objects);\n" + 
	"			return objects;\n" + 
	"		} catch (CustomException e) {\n" + 
	"		}\n" + 
	"		return null;\n" + 
	"	}\n" + 
	"\n" + 
	"}\n";
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
	
	public static String FORM_PANEL="package com.gui;\n" + 
			"\n" + 
			"import com.exception.CustomException;\n" + 
			"import java.time.ZoneId;\n" 
			+ "import com.model.*;\n" + 
			"import java.util.Arrays;\n" + 
			"import com.service.Services;\n" + 
			"import com.vaadin.data.Binder;\n" + 
			"import com.vaadin.data.converter.*;\n"
			+ "import com.vaadin.ui.ComboBox;\n" +  
			"import com.vaadin.ui.CheckBox;\n" + 
			"import com.vaadin.ui.DateField;\n" + 
			"import com.vaadin.ui.HorizontalLayout;\n" + 
			"import com.vaadin.ui.Notification;\n" + 
			"import com.vaadin.ui.TextField;\n" + 
			"\n" + 
			"public class ${"+CLASS_NAME+"}Form extends BasicForm<${"+CLASS_NAME+"}> {\n" + 
			"\n" + 
			"${"+COMPONENTS+"}" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"}Form(RefreshController refreshController) {\n" + 
			"		super( refreshController);\n"+
			"		refreshData();\n"+	
			"		initAndLayout();\n" + 
			"	}\n" + 
			"\n" + 
			"	private void initAndLayout() {\n" + 
			"${"+SET_CAPTIONS+"}\n" + 
			"		binder = new Binder<${"+CLASS_NAME+"}>(${"+CLASS_NAME+"}.class);\n" + 
			"${"+BINDINGS+"}" + 
			"		binder.bindInstanceFields(this);\n" + 
			"		setSizeUndefined();\n" + 
			"		HorizontalLayout buttons = new HorizontalLayout(save, delete);\n" + 
			"${"+ADD_COMPONENTS+"}\n" + 
			"	}\n" + 
			"\n" + 
			"	protected void delete() {\n" + 
			"		if(object!=null) {\n" + 
			"			try {\n" + 
			"				Services.getinstance().get${"+CLASS_NAME+"}Service().delete${"+CLASS_NAME+"}ById(object.getId());\n" + 
			"				refreshController.refresh();\n" + 
			"				setVisible(false);\n" + 
			"			} catch (CustomException e) {\n" + 
			"				Notification.show(e.getMessage());\n" + 
			"			}\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	protected ${"+CLASS_NAME+"} save() {\n" + 
			"		${"+CLASS_NAME+"} saved=null;\n" + 
			"		try {\n" + 
			"	if(object.getId()==null)\n		"
			+ "    saved = Services.getinstance().get${"+CLASS_NAME+"}Service().save${"+CLASS_NAME+"}(object);\n"
			+ "else \n"
					+ "  saved = Services.getinstance().get${"+CLASS_NAME+"}Service().update${"+CLASS_NAME+"}(object);\n" + 
			"			object.setId(saved.getId());\n" + 
			"			refreshController.refresh();\n" + 
			"			setVisible(false);\n" + 
			"		} catch (CustomException e) {\n" + 
			"			Notification.show(e.getMessage());\n" + 
			"		}\n" + 
			"		return saved;\n" + 
			"	}\n" + 
			"\n" + 
			"@Override\n" + 
			"	public void refreshData() {\n" 
			+ "${"+CUS_MANY_TO_ONE+"}" + 
			"		\n" + 
			"	}"+
			"}\n" + 
			"";
	
	public static String PANEL="package com.gui;\n" + 
			"\n" + 
			"\n" + 
			"import com.model.${"+CLASS_NAME+"};\n" + 
			"import com.vaadin.icons.VaadinIcons;\n" + 
			"import com.vaadin.ui.Button;\n" + 
			"import com.vaadin.ui.HorizontalLayout;\n" + 
			"import com.vaadin.ui.VerticalLayout;\n" + 
			"\n" + 
			"public class ${"+CLASS_NAME+"}Panel extends BasicPanel{\n" + 
			"\n" + 
			"	public ${"+CLASS_NAME+"}Panel() {\n" + 
			"		init();\n" + 
			"	}\n" + 
			"\n" + 
			"	private void init()  {\n" + 
			"		grid = new ${"+CLASS_NAME+"}Grid();\n" + 
			"		form = new ${"+CLASS_NAME+"}Form(this);\n" + 
			"		VerticalLayout layout = new VerticalLayout();\n" + 
			"		Button addBtn = new Button(\"Add new \",VaadinIcons.FILE_ADD);\n" + 
			"		addBtn.addClickListener(e -> {\n" + 
			"			grid.asSingleSelect().clear();\n" + 
			"			form.setObject(new ${"+CLASS_NAME+"}(),false);\n" + 
			"		});\n" + 
			"		HorizontalLayout toolbar = new HorizontalLayout( addBtn);\n" + 
			"		HorizontalLayout main = new HorizontalLayout(grid, form);\n" + 
			"		main.setSizeFull();\n" + 
			"		main.setExpandRatio(grid, 1);\n" + 
			"		layout.addComponents(toolbar, main);\n" + 
			"		setContent(layout);\n" + 
			"		form.setVisible(false);\n" + 
			"		grid.asSingleSelect().addValueChangeListener(event -> {\n" + 
			"			if (event.getValue() == null) {\n" + 
			"				form.setVisible(false);\n" + 
			"			} else {\n" + 
			"				form.setObject(event.getValue(),true);\n" + 
			"			}\n" + 
			"		});\n" + 
			"	}\n" + 
			"\n" + 
			"\n" + 
			"}\n" + 
			"";

}
