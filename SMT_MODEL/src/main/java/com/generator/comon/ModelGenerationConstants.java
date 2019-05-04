package com.generator.comon;

import java.util.UUID;

import javax.management.RuntimeErrorException;


public class ModelGenerationConstants {

	public static String MODEL_PACKAGE="package com.model;";

	public static String CLASS_NAME="className";
	public static String CLASS_NAME_START_LOWE="classNamestartLower";
	public static String UPPER_CLASS_NAME="upperClassName";
	public static String CLASS_BODY="classBody";

	public static String MODEL_BASIC_IMPORTS="import javax.persistence.Column;\n" + 
			"import javax.persistence.Entity;\n" + 
			"import javax.persistence.EnumType;\n" + 
			"import javax.persistence.Enumerated;\n" + 
			"import javax.persistence.GeneratedValue;\n" + 
			"import java.util.Date;\n" + 
			"import javax.persistence.GenerationType;\n" + 
			"import javax.persistence.Id;\n" + 
			"import javax.persistence.Table;\n"; 
	public static String ID_GETTER_SETTER="\nprotected Integer id;\n" + 
			"	public void setId(Integer id) {\n" + 
			"		this.id = id;\n" + 
			"	}\n" + 
			"	@Id\n" + 
			"	@GeneratedValue(strategy = GenerationType.AUTO)\n" + 
			"	public Integer getId() {\n" + 
			"		return id;\n" + 
			"	}";
	public static String MODEL_CLASS_TEMPLATE=MODEL_PACKAGE+" \n"+MODEL_BASIC_IMPORTS+"\n @Entity \n @Table(name = \"${"+UPPER_CLASS_NAME+"}\") \n public class ${"+CLASS_NAME+"} { \n "+ID_GETTER_SETTER+" ${"+CLASS_BODY+"} \n}";

	public static String ATTRIBUTE_UPPER="ATTRIBUTE_UPPER";
	public static String ATTRIBUTE_START_UPPER="ATTRIBUTE_START_UPPER";
	public static String ATTRIBUTE_START_LOWER="ATTRIBUTE_START_LOWER";
	public static String ATTRIBUTE_TYPE="ATTRIBUTE_TYPE";
	public static String MODEL_CLASS_TEMPLATE_GETTER_SETTER=""
			+ "private ${"+ATTRIBUTE_TYPE+"} ${"+ATTRIBUTE_START_LOWER+"};\n" + 
			"	@Column(name = \"${"+ATTRIBUTE_UPPER+"}\")\n" + 
			"	public ${"+ATTRIBUTE_TYPE+"} get${"+ATTRIBUTE_START_UPPER+"}() {\n" + 
			"		return ${"+ATTRIBUTE_START_LOWER+"};\n" + 
			"	}\n" + 
			"\n" + 
			"	public void set${"+ATTRIBUTE_START_UPPER+"}(${"+ATTRIBUTE_TYPE+"} ${"+ATTRIBUTE_START_LOWER+"}) {\n" + 
			"		this.${"+ATTRIBUTE_START_LOWER+"} = ${"+ATTRIBUTE_START_LOWER+"};\n" + 
			"	}";

	public static String decapitalize(String string) {
		if (string == null || string.length() == 0) {
			return string;
		}
		char c[] = string.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}

	public static String REPOSITORY_CLASS_TEMPLATE="package com.repository;\n" + 
			"\n" + 
			"import org.springframework.data.jpa.repository.JpaRepository;\n" + 
			"import org.springframework.data.querydsl.QueryDslPredicateExecutor;\n" + 
			"import org.springframework.stereotype.Repository;\n" + 
			"\n" + 
			"import com.model.${"+CLASS_NAME+"};\n" + 
			"\n" + 
			"@Repository\n" + 
			"public interface ${"+CLASS_NAME+"}Repository extends JpaRepository<${"+CLASS_NAME+"}, Integer>,QueryDslPredicateExecutor<${"+CLASS_NAME+"}>{\n" + 
			"	\n" + 
			"}";

	public static String SERVICE_TEMPLATE="package com.service;\n" + 
			"\n" + 
			"import java.util.List;\n" + 
			"\n" + 
			"import com.model.${"+CLASS_NAME+"};\n" + 
			"\n" + 
			"import com.exception.CustomException;\n" + 
			"\n" + 
			"public interface ${"+CLASS_NAME+"}Service {\n" + 
			"\n" + 
			"	public ${"+CLASS_NAME+"} save${"+CLASS_NAME+"}(${"+CLASS_NAME+"} object) throws CustomException;\n" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"} updateObject(${"+CLASS_NAME+"} object) throws CustomException;\n" + 
			"	\n" + 
			"	public List<${"+CLASS_NAME+"}> getAll${"+CLASS_NAME+"}() throws CustomException;\n" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"} get${"+CLASS_NAME+"}ById(Integer id) throws CustomException;\n" + 
			"	\n" + 
			"	public void delete${"+CLASS_NAME+"}ById(Integer id) throws CustomException;\n" + 
			"\n" + 
			"}";


	public static String SERVICE_IMPL_TEMPLATE="package com.service.impl;\n" + 
			"\n" + 
			"import java.util.List;\n" + 
			"\n" + 
			"import org.springframework.beans.factory.annotation.Autowired;\n" + 
			"import java.util.UUID;\n" + 
			"import com.exception.CustomException;\n" + 
			"import com.model.${"+CLASS_NAME+"};\n" + 
			"import com.repository.${"+CLASS_NAME+"}Repository;\n" + 
			"import com.service.${"+CLASS_NAME+"}Service;\n" + 
			"\n" + 
			"public class ${"+CLASS_NAME+"}ServiceImpl implements ${"+CLASS_NAME+"}Service{\n" + 
			"	@Autowired\n" + 
			"	private ${"+CLASS_NAME+"}Repository ${"+CLASS_NAME_START_LOWE+"}Repository;\n" + 
			"	\n" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"} save${"+CLASS_NAME+"}(${"+CLASS_NAME+"} object) throws CustomException {\n" + 
			"				object.setId(UUID.randomUUID().variant());\n" + 
			"       return ${"+CLASS_NAME_START_LOWE+"}Repository.save(object);\n" + 
			"	}\n" + 
			"\n" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"} updateObject(${"+CLASS_NAME+"} object) throws CustomException {\n" + 
			"		return ${"+CLASS_NAME_START_LOWE+"}Repository.save(object);\n" + 
			"	}\n" + 
			"\n" + 
			"	\n" + 
			"	public List<${"+CLASS_NAME+"}> getAll${"+CLASS_NAME+"}() throws CustomException {\n" + 
			"		return ${"+CLASS_NAME_START_LOWE+"}Repository.findAll();\n" + 
			"	}\n" + 
			"\n" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"} get${"+CLASS_NAME+"}ById(Integer id) throws CustomException {\n" + 
			"		return ${"+CLASS_NAME_START_LOWE+"}Repository.findOne(id);\n" + 
			"	}\n" + 
			"\n" + 
			"	\n" + 
			"	public void delete${"+CLASS_NAME+"}ById(Integer id) throws CustomException {\n" + 
			"		${"+CLASS_NAME_START_LOWE+"}Repository.delete(id);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	public static String SERVICES_GETTER_SETTER="	private ${"+CLASS_NAME+"}Service ${"+CLASS_NAME_START_LOWE+"}Service;\n" + 
			"	\n" + 
			"	public ${"+CLASS_NAME+"}Service get${"+CLASS_NAME+"}Service() {\n" + 
			"		return ${"+CLASS_NAME_START_LOWE+"}Service;\n" + 
			"	}\n" + 
			"	\n" + 
			"	public void set${"+CLASS_NAME+"}Service(${"+CLASS_NAME+"}Service ${"+CLASS_NAME_START_LOWE+"}Service) {\n" + 
			"		this.${"+CLASS_NAME_START_LOWE+"}Service = ${"+CLASS_NAME_START_LOWE+"}Service;\n" + 
			"	}";

	public static String SERVICES="package com.service;\n" + 
			"\n" + 
			"import java.time.Duration;\n" + 
			"import java.time.Instant;\n" + 
			"import javax.management.RuntimeErrorException;\n" + 
			"\n" + 
			"import org.springframework.context.support.ClassPathXmlApplicationContext;\n" + 
			"\n" + 
			"public class Services {\n" + 
			"	private static Services services;\n" + 
			"	\n" + 
			"	public static Services getinstance() {\n" + 
			"		if(services==null) {\n" + 
			"			try{\n" + 
			"				new ClassPathXmlApplicationContext(\"com/spring/spring.xml\");  \n" + 
			"			}catch (Exception e) {\n" + 
			"				throw new RuntimeErrorException(null, e.getMessage());\n" + 
			"			}\n" + 
			"			}\n" + 
			"		return services;\n" + 
			"	}\n" + 
			"	\n" + 
			"	private Services() {\n" + 
			"		services=this;\n" + 
			"	}\n" + 
			"	\n ${"+CLASS_BODY+"}" + 
			"}\n" + 
			"";

	public static String STRING_SERVICES="springServices";
	public static String STRING_REF="springRef";

	public static String XML_SERVICES_BEANS="<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" + 
			"<beans xmlns=\"http://www.springframework.org/schema/beans\"  \n" + 
			"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  \n" + 
			"    xsi:schemaLocation=\"http://www.springframework.org/schema/beans   \n" + 
			"http://www.springframework.org/schema/beans/spring-beans.xsd\">  \n" + 
			"  \n" + 
			"${"+STRING_SERVICES+"}" + 
			"\n" + 
			"<bean id=\"Services\" class=\"com.service.Services\" >\n" + 
			"${"+STRING_REF+"}" + 
			"</bean> \n" + 
			"\n" + 
			"</beans>  ";

	public static String SERVICE_BEAN="<bean id=\"${"+CLASS_NAME+"}ServiceBean\" class=\"com.service.impl.${"+CLASS_NAME+"}ServiceImpl\"></bean> \n";

	public static String SERVICE_BEAN_REF="<property name=\"${"+CLASS_NAME_START_LOWE+"}Service\" ref=\"${"+CLASS_NAME+"}ServiceBean\" />\n";

}
