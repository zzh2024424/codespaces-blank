package solid;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
//:! Copyright.txt This computer source code is Copyright zhou zhaohui (China hunan ) @2024-6-16 All Rights Reserved you can use if be allowed

public class generate_boxs {      
	public static void main(String[] args)
	  {
	      EventQueue.invokeLater(new Runnable()
	          {    
	               public void run()
	               {
	            	   Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> objects=(new boxs_generate()).create();
	                     JFrame frame=new Group_and_Layer_Display(objects,1920,1080);
	                     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           frame.setResizable(true);
                           frame.setVisible(true);
                           frame.toFront();
                           frame.setLocation(0,0);

	                 }
	             });
	          }
}

class boxs_generate 
{
   public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> create()
  {
  String fileName="D:\\boxs.txt";
  try (Scanner sc = new Scanner(new File(fileName))) {
      sc.useDelimiter("[,;]");  //分隔符
         man[0]=sc.nextDouble();
         man[1]=sc.nextDouble();
   
     while (sc.hasNext()) { 
    	 type=sc.next();
    	 if(type.equals("normal"))
    	 {  ID=sc.next();
    		 parameter_box[0]=sc.nextDouble();
    	 parameter_box[1]=sc.nextDouble();
    	 parameter_box[2]=sc.nextDouble();
    	 coordinate[0]=sc.nextDouble();
    	 coordinate[1]=sc.nextDouble();
    	 coordinate[2]=sc.nextDouble();
    	 draw_color[0]=sc.nextInt();
    	 draw_color[1]=sc.nextInt();
    	 draw_color[2]=sc.nextInt();
    	 fill_color[0]=sc.nextInt();
    	 fill_color[1]=sc.nextInt();
    	 fill_color[2]=sc.nextInt();
    	 transparency=sc.nextFloat();
    	 translate[0]=0;
    	 translate[1]=0;
    	 translate[2]=0;
    	 translate[3]=0;
    	 translate[4]=0;
    	 translate[5]=0;
    	 double[] parameter=new double[] {parameter_box[0],parameter_box[1],parameter_box[2],coordinate[0],coordinate[1],coordinate[2]};
    	 boolean[] face_if_fill=new boolean[] {true,true,true,true,true,true};
    	 Cuboid_Draw box=(new Cuboid_Create(parameter,translate,man,draw_color,fill_color,transparency,face_if_fill)).Create();
    	 all_box.put(ID, box);
    	 };
    
    	 if(type.equals("rotate"))
    	 {
    		 ID=sc.next();
    		 parameter_box[0]=sc.nextDouble();
    	 parameter_box[1]=sc.nextDouble();
    	 parameter_box[2]=sc.nextDouble();
    	 coordinate[0]=sc.nextDouble();
    	 coordinate[1]=sc.nextDouble();
    	 coordinate[2]=sc.nextDouble();
    	 draw_color[0]=sc.nextInt();
    	 draw_color[1]=sc.nextInt();
    	 draw_color[2]=sc.nextInt();
    	 fill_color[0]=sc.nextInt();
    	 fill_color[1]=sc.nextInt();
    	 fill_color[2]=sc.nextInt();
    	 transparency=sc.nextFloat();
    	 translate[0]=sc.nextDouble();
    	 translate[1]=sc.nextDouble();
    	 translate[2]=sc.nextDouble();
    	 translate[3]=sc.nextDouble();
    	 translate[4]=sc.nextDouble();
    	 translate[5]=sc.nextDouble();
    	 double[] parameter=new double[] {parameter_box[0],parameter_box[1],parameter_box[2],coordinate[0],coordinate[1],coordinate[2]};
    	 boolean[] face_if_fill=new boolean[] {true,true,true,true,true,true};
    	 Cuboid_Draw box=(new Cuboid_Create(parameter,translate,man,draw_color,fill_color,transparency,face_if_fill)).Create();
    	 all_box.put(ID, box);
    	
    	 };
            }
              }
      catch(FileNotFoundException exception)
        { 
        exception.printStackTrace();
         };
        
 		   Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer1=(new New_group_and_layer(new TreeMap<String,TreeMap<String,TreeMap<String,Object_Draw>>>())).Create();
	         Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer2=(new Create_Group(group_and_layer1,"group1")).Create();
	         Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer3=(new Creat_a_Layer_on_an_Group(group_and_layer2,"group1","test")).create();
	         Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer4=(new Put_entry_layer(group_and_layer3,"group1","test",all_box)).put_to();
	         return group_and_layer4;
  }
   
  private double[] man=new double[2],parameter_box=new double[3],coordinate=new double[3],translate=new double[6];
   private int[] draw_color=new int[3],fill_color=new int[3];
   private String ID,type;
   private float transparency;
   private TreeMap<String,Object_Draw> all_box=new TreeMap<String,Object_Draw>();
}


class Cuboid_Create {
	public Cuboid_Create(double an_parameter[],double an_translate[], double an_man[],int an_draw_color[],int an_color_fill[],float an_transparency,boolean an_face_if_fill[])
	{   parameter=Arrays.copyOf(an_parameter,an_parameter.length);
		translate=Arrays.copyOf(an_translate,an_translate.length);
		man=Arrays.copyOf(an_man,an_man.length);
		color_draw=Arrays.copyOf(an_draw_color,an_draw_color.length);
		color_fill=Arrays.copyOf(an_color_fill,an_color_fill.length);
		transparency=an_transparency;
		face_if_fill=Arrays.copyOf(an_face_if_fill,an_face_if_fill.length);
	}

	public String getDescription()
	{return"this is a cuboid";}
	public Cuboid_Draw Create()
	{
		Point_cuboid cuboid=new Point_cuboid(parameter);
        double[][] cuboid_point=cuboid.point_confirm();
        double[][] cuboid_face_center=cuboid.face_center_confirm();
        double[] per_point=(new perspective_point_tran(cuboid_point,translate,man)).perspective_caculate();
        double[][] face_point_tran=(new translate(cuboid_face_center,translate)).caculate();
        cuboidMaker cuboid_m=new cuboidMaker(per_point);
        GeneralPath[] cuboid_face=cuboid_m.makeObject();
        Visible_cuboid visible_c=new Visible_cuboid(translate,man,face_point_tran);
        boolean[] cuboid_vis=visible_c.makeVisible();
        Cuboid_Draw cuboid_draw=new Cuboid_Draw(cuboid_face,color_draw,cuboid_vis,face_if_fill,color_fill,transparency);
        return cuboid_draw;
	}
	
private double[] parameter=new double[6];
private double[] translate=new double[6];
private double[] man=new double[2];
private int[] color_draw=new int[3];
private int[] color_fill=new int[3];
private float transparency;
private boolean[] face_if_fill=new boolean[6];
}

abstract class Point_object {
public Point_object()
{}
public abstract double[][] point_confirm();
public String toString()
{
  return getClass().getName();	
}
}

class Point_cuboid extends Point_object
{
public Point_cuboid(double parameter[])
{L=parameter[0];
W=parameter[1];
H_cub=parameter[2];
EyeD=parameter[3];  
DisP=parameter[4];  
H=parameter[5];}    
public double[][] point_confirm()
{double coordinate[ ][ ]=
    {
    	       {EyeD-L/2,EyeD+L/2,EyeD+L/2,EyeD-L/2,EyeD-L/2,EyeD+L/2,EyeD+L/2,EyeD-L/2},
    	       {DisP-W/2,DisP-W/2,DisP+W/2,DisP+W/2,DisP-W/2,DisP-W/2,DisP+W/2,DisP+W/2},
    	       {H,H,H,H,H+H_cub,H+H_cub,H+H_cub,H+H_cub}
    	     };
 return coordinate;}
public double[][] face_center_confirm()
{double face_center_point[][]=
{
		{EyeD+L/2,EyeD-L/2,EyeD,EyeD,EyeD,EyeD},
		{DisP,DisP,DisP+W/2,DisP-W/2,DisP,DisP},
		{H+H_cub/2,H+H_cub/2,H+H_cub/2,H+H_cub/2,H+H_cub,H}
  };
 return face_center_point;
}


private double L,W,H_cub,EyeD,DisP,H;
private double[][] coordinate,face_center_point;
	
}


class translate {
	 public translate(double points[][],double translate[])
    {  Num_p=points[0].length;
		 for(int i=0;i<points.length;i++)
	            coordinate[i]=Arrays.copyOf(points[i],points[i].length);
      local_x=translate[0];
      local_y=translate[1];
      local_z=translate[2];
      angle_x=translate[3];
      angle_y=translate[4];
      angle_z=translate[5];
       
      }
		 //2024-3-25 add below void
	 public translate()
	 {}
	 public double[][] caculate()
    {
    
     
     double angletrans_x,angletrans_y,angletrans_z,xsin,ysin,zsin,xcos,ycos,zcos;
   
    
angletrans_x=(angle_x*Math.PI/180);
xcos=Math.cos(angletrans_x);
xsin=Math.sin(angletrans_x);
angletrans_y=(angle_y*Math.PI/180);
ycos=Math.cos(angletrans_y);
ysin=Math.sin(angletrans_y);
angletrans_z=(angle_z*Math.PI/180);
zcos=Math.cos(angletrans_z);
zsin=Math.sin(angletrans_z);

double transform_x[ ][ ]=
{
   {1,0,0},
  {0,xcos,-xsin},
  {0,xsin,xcos}
 
 };
double transform_y[ ][ ]=
{
  {ycos,0,ysin},
  {0,1,0},
  {-ysin,0,ycos}
  
 };
double transform_z[ ][ ]=
{
  {zcos,-zsin,0},
  {zsin,zcos,0},
  {0,0,1}
 };
 local=new double[3];
 local[0]=local_x;
 local[1]=local_y;
 local[2]=local_z;
 for(int i=0;i<coordinate.length;i++)
    for(int j=0;j<coordinate[0].length;j++)
       coordinate[i][j]-=local[i];          
 double coordinate_end_x[ ][ ]=new double[transform_x.length][coordinate[0].length];
 for(int i=0;i<transform_x.length;i++)
   for(int j=0;j<coordinate[0].length;j++)
      for(int k=0;k<coordinate.length;k++)
         coordinate_end_x[i][j]+=transform_x[i][k]*coordinate[k][j];
  double coordinate_end_y[ ][ ]=new double[transform_y.length][coordinate[0].length];
 for(int i=0;i<transform_y.length;i++)
   for(int j=0;j<coordinate[0].length;j++)
      for(int k=0;k<coordinate.length;k++)
         coordinate_end_y[i][j]+=transform_y[i][k]*coordinate_end_x[k][j];
    double coordinate_end_z[ ][ ]=new double[transform_y.length][coordinate[0].length];
  for(int i=0;i<transform_z.length;i++)
   for(int j=0;j<coordinate[0].length;j++)
      for(int k=0;k<coordinate.length;k++)
         coordinate_end_z[i][j]+=transform_z[i][k]*coordinate_end_y[k][j];
  double coordinate_end[ ][ ]=new double[coordinate.length][coordinate[0].length];
   for(int i=0;i<coordinate.length;i++)
        for(int j=0;j<coordinate[0].length;j++)
          coordinate_end[i][j]=coordinate_end_z[i][j];
  for(int i=0;i<coordinate.length;i++)
    for(int j=0;j<coordinate[0].length;j++)
       coordinate_end[i][j]+=local[i];   
  return coordinate_end;
    }
    
	 protected int Num_p;
    protected double L,W,H,EyeD,DisP,H_cub,dw,dh;
	 protected double angle_x,angle_y,angle_z;
     protected double local_x,local_y,local_z;
     protected double[] local;
   protected double[][] coordinate=new double[3][Num_p],coordinate_end=new double[3][Num_p];
 }



class perspective_point_tran extends translate {
	 public  perspective_point_tran(double points[][],double translate[],double man[])
	 {  super(points,translate);
		 Num_p_other=points[0].length;
	 for(int i=0;i<points.length;i++)
	     coordinate_other[i]=Arrays.copyOf(points[i],points[i].length);
	local_x_other=translate[0];
	local_y_other=translate[1];
	local_z_other=translate[2];
	angle_x_other=translate[3];
	angle_y_other=translate[4];
	angle_z_other=translate[5];
	 Heye=man[0];
	 De=man[1];}
	 
	
		 //below void is 2024-3-25 add
	 public  perspective_point_tran()
	 {}
	 
	 public double[] perspective_caculate()
	 {      double angletrans_x,angletrans_y,angletrans_z,xsin,ysin,zsin,xcos,ycos,zcos;
	 
	 
	angletrans_x=(angle_x_other*Math.PI/180);
	xcos=Math.cos(angletrans_x);
	xsin=Math.sin(angletrans_x);
	angletrans_y=(angle_y_other*Math.PI/180);
	ycos=Math.cos(angletrans_y);
	ysin=Math.sin(angletrans_y);
	angletrans_z=(angle_z_other*Math.PI/180);
	zcos=Math.cos(angletrans_z);
	zsin=Math.sin(angletrans_z);

	double transform_x[ ][ ]=
	{
	 {1,0,0},
	{0,xcos,-xsin},
	{0,xsin,xcos}

	};
	double transform_y[ ][ ]=
	{
	{ycos,0,ysin},
	{0,1,0},
	{-ysin,0,ycos}

	};
	double transform_z[ ][ ]=
	{
	{zcos,-zsin,0},
	{zsin,zcos,0},
	{0,0,1}
	};
	local_other=new double[3];
	local_other[0]=local_x_other;
	local_other[1]=local_y_other;
	local_other[2]=local_z_other;

	for(int i=0;i<coordinate_other.length;i++)
	  for(int j=0;j<coordinate_other[0].length;j++)
	     coordinate_other[i][j]-=local_other[i];          
	double coordinate_other_end_x[ ][ ]=new double[transform_x.length][coordinate_other[0].length];
	for(int i=0;i<transform_x.length;i++)
	 for(int j=0;j<coordinate_other[0].length;j++)
	    for(int k=0;k<coordinate_other.length;k++)
	       coordinate_other_end_x[i][j]+=transform_x[i][k]*coordinate_other[k][j];
	double coordinate_other_end_y[ ][ ]=new double[transform_y.length][coordinate_other[0].length];
	for(int i=0;i<transform_y.length;i++)
	 for(int j=0;j<coordinate_other[0].length;j++)
	    for(int k=0;k<coordinate_other.length;k++)
	       coordinate_other_end_y[i][j]+=transform_y[i][k]*coordinate_other_end_x[k][j];
	  double coordinate_other_end_z[ ][ ]=new double[transform_y.length][coordinate_other[0].length];
	for(int i=0;i<transform_z.length;i++)
	 for(int j=0;j<coordinate_other[0].length;j++)
	    for(int k=0;k<coordinate_other.length;k++)
	       coordinate_other_end_z[i][j]+=transform_z[i][k]*coordinate_other_end_y[k][j];
	double coordinate_other_end[ ][ ]=new double[coordinate_other.length][coordinate_other[0].length];
	 for(int i=0;i<coordinate_other.length;i++)
	      for(int j=0;j<coordinate_other[0].length;j++)
	        coordinate_other_end[i][j]=coordinate_other_end_z[i][j];
	for(int i=0;i<coordinate_other.length;i++)
	  for(int j=0;j<coordinate_other[0].length;j++)
	     coordinate_other_end[i][j]+=local_other[i];   
		 
		 Num=coordinate_other_end[0].length;
		 D=new double[Num];
	 xp_and_yp=new double[Num*2];
	  for(int l=0;l<Num;l++)
	       D[l]=De/(De+coordinate_other_end[1][l]);
	    for(int m=0;m<Num;m++)
	       xp_and_yp[m]=coordinate_other_end[0][m]*D[m];
	    for(int n=Num;n<Num*2;n++)
	       xp_and_yp[n]=(coordinate_other_end[2][n%Num]-Heye)*D[n%Num]+Heye;
		     for(int p=0;p<Num;p++)
	       {
	         xp_and_yp[p]=xp_and_yp[p]+dw;
	         xp_and_yp[p+Num]=dh-xp_and_yp[p+Num];
	        };
	        return xp_and_yp;}//2023-9-3 20:22 note xp_and_yp first half is xp ,the latter half is yp 
	 
	 
	
	
	 
	 private double angle_x_other,angle_y_other,angle_z_other;
	 private double local_x_other,local_y_other,local_z_other;
	 private double[] local_other;
	 private int Num_p, Num,Num_p_other;
	private  double[][] coordinate_other=new double[3][Num_p_other];
	private double Heye,De;
	public double dw=960,dh=850;
	private double[] D,xp_and_yp;
	}


 abstract class ObjectMaker {
public ObjectMaker()
{}
public abstract GeneralPath[] makeObject();
 public String toString()
 {
	 return getClass().getName();
 }
}

class cuboidMaker extends ObjectMaker{
	public cuboidMaker(double coordinate[])
	{for(int i=0;i<coordinate.length/2;i++)
        point[0][i]=coordinate[i];
	for(int i=coordinate.length/2;i<coordinate.length;i++)
		point[1][i%(coordinate.length/2)]=coordinate[i];}
	public GeneralPath[] makeObject()
	{
		double[][][]   point_draw= {
    			{{point[0][1],point[0][2],point[0][6],point[0][5]},{point[1][1],point[1][2],point[1][6],point[1][5]},},
    			{{point[0][0],point[0][3],point[0][7],point[0][4]},{point[1][0],point[1][3],point[1][7],point[1][4]},},
                {{point[0][2],point[0][6],point[0][7],point[0][3]},{point[1][2],point[1][6],point[1][7],point[1][3]},},
                {{point[0][0],point[0][1],point[0][5],point[0][4]},{point[1][0],point[1][1],point[1][5],point[1][4]},},
                {{point[0][4],point[0][5],point[0][6],point[0][7]},{point[1][4],point[1][5],point[1][6],point[1][7]},},
                {{point[0][0],point[0][1],point[0][2],point[0][3]},{point[1][0],point[1][1],point[1][2],point[1][3]},}
    	};
    	for(int i=0;i<6;i++)
       {GeneralPath generalp=new GeneralPath(GeneralPath.WIND_EVEN_ODD,point_draw[i][0].length);
       generalp.moveTo(point_draw[i][0][0], point_draw[i][1][0]);
       for (int index = 1; index < point_draw[i][0].length; index++) {
    	   generalp.lineTo(point_draw[i][0][index], point_draw[i][1][index]);
       };
       generalp.closePath();
       generalpath[i]=generalp;}
    	return generalpath;
	}
	private GeneralPath[] generalpath=new GeneralPath[6];
	private double[][] point=new double[2][8];
}


 abstract class Visible {
public Visible()
{}
public abstract boolean[] makeVisible();

public String toString()
{
	 return getClass().getName();
}
}

class Visible_cuboid extends Visible{
	public Visible_cuboid(double translate[],double man[],double point_center[][])
	{ Num=point_center[0].length;
	 for(int i=0;i<point.length;i++)
	     point[i]=Arrays.copyOf(point_center[i],point_center[i].length); 
		 Heye=man[0];
	     De=man[1]; 
	 local_x=translate[0];
	 local_y=translate[1];
	 local_z=translate[2];
	 angle_x=translate[3];
	 angle_y=translate[4];
	 angle_z=translate[5];}
	
	public boolean[] makeVisible()
	{
		double angletrans_x,angletrans_y,angletrans_z,xsin,ysin,zsin,xcos,ycos,zcos;
		 angletrans_x=(angle_x*Math.PI/180);
		    xcos=Math.cos(angletrans_x);
		    xsin=Math.sin(angletrans_x);
		    angletrans_y=(angle_y*Math.PI/180);
		    ycos=Math.cos(angletrans_y);
		    ysin=Math.sin(angletrans_y);
		    angletrans_z=(angle_z*Math.PI/180);
		    zcos=Math.cos(angletrans_z);
		    zsin=Math.sin(angletrans_z);
		   	   double[][] normalline= {{1,-1,0,0,0,0},
				        {0,0,1,-
		   		   1,0,0},
				        {0,0,0,0,1,-1}};
		   	  double[][] normalline_trans= {{ycos*zcos,-ycos*zcos,xsin*ysin*zcos-xcos*zsin,-xsin*ysin*zcos+xcos*zsin,xcos*ysin*zcos+xsin*zsin,-xcos*ysin*zcos-xsin*zsin},
				                            {ycos*zsin,-ycos*zsin,xsin*ysin*zsin+xcos*zcos,-xsin*ysin*zsin-xcos*zcos,xcos*ysin*zsin-xsin*zcos,-xcos*ysin*zsin+xsin*zcos},
				                            {-ysin,ysin,xsin*ycos,-xsin*ycos,xcos*ycos,-xcos*ycos}};
		   	  
		   		  for(int k=0;k<6;k++)
		   		  {vector_point[0][k]=point[0][k];
		   		   vector_point[1][k]=point[1][k]+De;
		   		   vector_point[2][k]=point[2][k]-Heye;
		   		   };
		   		double[] angle_included=new double[Num];double[] numerator=new double[Num];double[] denominator=new double[Num];
		   		   for(int i=0;i<Num;i++)
		   		   {  
		   			   numerator[i]=normalline_trans[0][i]*vector_point[0][i]+normalline_trans[1][i]*vector_point[1][i]+normalline_trans[2][i]*vector_point[2][i];
		   		   denominator[i]=Math.sqrt(normalline_trans[0][i]*normalline_trans[0][i]+normalline_trans[1][i]*normalline_trans[1][i]+normalline_trans[2][i]*normalline_trans[2][i])*Math.sqrt(vector_point[0][i]*vector_point[0][i]+vector_point[1][i]*vector_point[1][i]+vector_point[2][i]*vector_point[2][i]);
		   		   angle_included[i]=Math.acos(numerator[i]/denominator[i]);};
		            for(int i=0;i<Num;i++)
		            {if(angle_included[i]>Math.PI/2)  
		            	{if_visible[i]=true;}
		            else
		            	{if_visible[i]=false;};}
		            return if_visible;}
	int Num;
	 double Heye,De;
	 double angle_x,angle_y,angle_z;
	 double local_x,local_y,local_z;
	 double[][] point=new double[3][Num];double[][] vector_point=new double[3][6];boolean[] if_visible=new boolean[6];
}

class Object_Draw extends JComponent{
	public Object_Draw()
	{}
	public String toString()
	{
	  return getClass().getName();	
	}

}

class Cuboid_Draw extends Object_Draw{
	public Cuboid_Draw(GeneralPath face_re[],int color[],boolean visible[],boolean if_fill[],int color_fill[],float transparency)
	{    Num=6;
		 face=Arrays.copyOf(face_re,face_re.length);
    	 for(int i=0;i<visible.length;i++)
    		 visible_if[i]=visible[i];
    	 for(int i=0;i<if_fill.length;i++)
    		 fill_if[i]=if_fill[i];
    	   R=color[0];
           G=color[1];
           B=color[2];
           R_fill=color_fill[0];
           G_fill=color_fill[1];
           B_fill=color_fill[2];
           transp=transparency;
           
	}
	public void paintComponent(Graphics g)    
    
    { 
 	   Graphics2D g2=(Graphics2D) g; 
 	  /**2023-10-26 add antialias_on*/
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
 	 	for(int i=0;i<Num;i++)
    {if(visible_if[i]) {
    
    if(fill_if[i]) {int rule=AlphaComposite.SRC_OVER;
    float alpha=transp;
    g2.setComposite(AlphaComposite.getInstance(rule,alpha));
    g2.setPaint(new Color(R_fill,G_fill,B_fill));
    g2.fill(face[i]);};
    g2.setPaint(new Color(R,G,B));
    g2.draw(face[i]);
                        }
    }
 	 	}
    int R,G,B,Num;
    GeneralPath[] face=new GeneralPath[Num]; boolean[] visible_if=new boolean[6];boolean[] fill_if=new boolean[6];
    private int R_fill,G_fill,B_fill;
    private float transp;
}


class Group_and_Layer_Display extends JFrame
{
	
	public Group_and_Layer_Display(Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> an_group_and_layer,int a_wide_frame,int a_high_frame)
{  wide_frame=a_wide_frame;
   high_frame=a_high_frame;
  
		setSize(wide_frame,high_frame);
	group_and_layer=an_group_and_layer;	
	for(Map.Entry<String,TreeMap<String,TreeMap<String,Object_Draw>>> entry: group_and_layer.entrySet()) {
    	Set<Map.Entry<String,TreeMap<String,Object_Draw>>> entry_get=(entry.getValue()).entrySet();    
    	for(Map.Entry<String,TreeMap<String,Object_Draw>> entry2:entry_get) {
    		Set<Map.Entry<String,Object_Draw>> entry2_get=(entry2.getValue()).entrySet();
    		for(Map.Entry<String,Object_Draw> entry3:entry2_get) {
    		  add(entry3.getValue());
	            setVisible(true);
    	                                                        };
    	                                                                        };
                                                                                                              };
}

private int wide_frame,high_frame;
private Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer;
}

class New_group_and_layer
{
public 	New_group_and_layer(Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> aTreeMap)
{TreeMap=aTreeMap;}
public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> Create(){
	Group_and_Layer g_and_l=new Group_and_Layer(TreeMap);
    Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer_c_default=g_and_l.Creat_default_group();
    return group_and_layer_c_default;
}
private Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> TreeMap;
}

class Create_Group
{
public Create_Group(Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> an_group_and_layer,String aGroupName)
{
group_and_layer=an_group_and_layer;
GroupName=aGroupName;
}
public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> Create(){
	Group_and_Layer g_and_l=new Group_and_Layer(group_and_layer);
	Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer2=g_and_l.Creat_Group(GroupName);
	return group_and_layer2;
}

private Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer;
private String GroupName;
}


class Creat_a_Layer_on_an_Group
{
public Creat_a_Layer_on_an_Group(Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> an_group_and_layer,String aGroupName,String aLayerName)
{group_and_layer=an_group_and_layer;
GroupName=aGroupName;
LayerName=aLayerName;
}
public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> create()
{
	group_and_layer.get(GroupName).put(LayerName, new TreeMap<String,Object_Draw>());
	return 	group_and_layer;	
}

private Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer;
private String GroupName;
private String LayerName;
}

class Put_entry_layer
{
public Put_entry_layer(Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> an_group_and_layer,String aGroupName,String aLayerName,TreeMap<String,Object_Draw> a_entryLayer)
{group_and_layer=an_group_and_layer;
GroupName=aGroupName;
LayerName=aLayerName;
entrylayer=a_entryLayer;}
public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> put_to()
{Group_and_Layer g_and_l=new Group_and_Layer(group_and_layer);
Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer2=g_and_l.put_entry_layer(LayerName,GroupName,entrylayer);
return group_and_layer2;
}
public TreeMap<String,Object_Draw> recive()
{return entrylayer;}
private Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer;
private String GroupName;
private String LayerName;
public TreeMap<String,Object_Draw> entrylayer;
}

 class Group_and_Layer {
	public Group_and_Layer(Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> aTreeMap) 
	{
		group_and_layer=aTreeMap;
	}
	public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> Creat_default_group()
	{group_and_layer.put("default",new TreeMap<String,TreeMap<String,Object_Draw>>());
	return group_and_layer;
	}
public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> Creat_Layer(String aLayerName)
{
	group_and_layer.get("default").put(aLayerName, new TreeMap<String,Object_Draw>());
return group_and_layer;

}

public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> Creat_Group(String aGroupName)
{
	group_and_layer.put(aGroupName, new TreeMap<String,TreeMap<String,Object_Draw>>());
return group_and_layer;

}

public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> Creat_Group_and_add(String GroupName)
{  
	
	group_and_layer.put(GroupName,new TreeMap<String,TreeMap<String,Object_Draw>>());
	return group_and_layer;
}

public Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> put_entry_layer(String aLayerName,String aGroupName,TreeMap<String,Object_Draw> entrylayer)
{   String LayerName=aLayerName;
    String GroupName=aGroupName;
	group_and_layer.get(GroupName).get(LayerName).putAll(entrylayer);
	
return group_and_layer;
}

private Map<String,TreeMap<String,TreeMap<String,Object_Draw>>> group_and_layer;

}
