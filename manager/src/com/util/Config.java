package com.util;

import java.io.File;

public class Config {
	
	static{
	
		File index=new File(".\\index");
		File data2=new File(".\\student_data");
		File data3=new File(".\\teacher_data");
		File data4=new File(".\\course_data");
		File data5=new File(".\\coursechoose_data");
		File data6=new File(".\\other_data");
		File data7=new File(".\\coursetime_data");
		index.mkdir();
		data2.mkdir();
		data3.mkdir();
		data4.mkdir();
		data5.mkdir();
		data6.mkdir();
		data7.mkdir();
	}


	public static final String 	COL_INFORMASTION_FILENAME=".\\other_data\\col_information.txt";//学院信息文件的存储路径
	public static final String 	CLS_INFORMASTION_FILENAME=".\\other_data\\cls_information.txt";//班级信息文件的存储路径
	public static final String 	MAJ_INFORMASTION_FILENAME=".\\other_data\\maj_information.txt";//专业信息文件的存储路径
	
	public static final String 	STU_INFORMASTION_CODEINDEX__FILENAME=".\\index\\stu_codeindex.txt";//学生信息按学号索引文件的存储路径
	public static final String 	STU_INFORMASTION_NAMEINDEX_FILENAME=".\\index\\stu_nameindex.txt";//学生信息按名字索引文件的存储路径
	public static final String 	TEA_INFORMASTION_CODEINDEX_FILENAME=".\\index\\tea_codeindex.txt";//教师信息按工号索引文件的存储路径
	public static final String 	TEA_INFORMASTION_NAMEINDEX_FILENAME=".\\index\\tea_nameindex.txt";//教师信息按名字索引文件的存储路径
	public static final String 	TEA_INFORMASTION_COUNAMEINDEX_FILENAME=".\\index\\tea_counameindex.txt";//教师信息按名字索引文件的存储路径
	public static final String 	COU_INFORMASTION_CODEINDEX_FILENAME=".\\index\\cou_codeindex.txt";//课程信息按课程号索引文件的存储路径
	public static final String 	COU_INFORMASTION_NAMEINDEX_FILENAME=".\\index\\cou_nameindex.txt";//课程信息按课程名字索引文件的存储路径
	public static final String 	COUTIME_INFORMASTION_CODEINDEX_FILENAME=".\\index\\coutime_codeindex.txt";//上课时间信息按课程号索引文件的存储路径
	public static final String 	COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME=".\\index\\couchoose_stucodeindex.txt";//选课信息文件按学号索引的存储路径
	public static final String 	COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME=".\\index\\couchoose_teacodeindex.txt";//选课信息按教师工号索引文件的存储路径
	public static final String 	COUCHOOSE_INFORMASTION_STUINDEX_FILENAME=".\\index\\couchooose_coucodeindex.txt";//选课信息按课程号索引文件的存储路径
	public static final String 	COL_INFORMASTION_CODEINDEX_FILENAME=".\\index\\col_codeindex.txt";//学院信息按学院号索引文件的存储路径
	public static final String 	CLS_INFORMASTION_CODEINDEX_FILENAME=".\\index\\cls_codeindex.txt";//班级信息按班级号文件的存储路径
	public static final String 	MAJ_INFORMASTION_CODEINDEX_FILENAME=".\\index\\maj_codeindex.txt";//专业信息按专业号索引文件的存储路径


			
			
}
