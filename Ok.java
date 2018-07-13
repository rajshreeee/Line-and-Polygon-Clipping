package LiangBarsky;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import rrassi2.ellipse;
import rrassi2.translation1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

public class Ok implements GLEventListener{


	int x1=-80,x2=0,y3=-80,y2=0;
	float u1=0,u2=1;
	int xmin=-50,ymin=-50,xmax=50,ymax=50;
	double [] p = new double [4];
	double [] q = new double [4];
	
	private GLU glu;
	
	
	void clip(GL2 gl,int x1,int y1,int x2,int y2)
	{
	    int dx=x2-x1,dy=y2-y1,i;
	    double t;
	    p[0]=-dx;q[0]=x1-xmin;
	    p[1]=dx;q[1]=xmax-x1;
	    p[2]=-dy;q[2]=y1-ymin;
	    p[3]=dy;q[3]=ymax-y1;

	    for(i=0;i<4;i++)
	    {
	        if(p[i]==0 && q[i]<0)
	            return;
	        if(p[i]<0)
	        {
	            t=(q[i])/(p[i]);  // This calculation was returning a zero because both q and p were int
	            if(t>u1 && t<u2)
	                {u1=(float) t;}
	        }
	        else if(p[i]>0)
	        {
	            t=(q[i])/(p[i]);  // This calculation was returning a zero because both q and p were int
	            if(t>u1 && t<u2)
	                {u2=(float) t;}
	        }
	    }
	    if(u1<u2)
	    {
	        x1=(int) (x1+u1*(x2-x1));
	        y1=(int) (y1+u1*(y2-y1));
	        x2=(int) (x1+u2*(x2-x1));
	        y2=(int) (y1+u2*(y2-y1));
	        gl.glColor3f(1.0f, 0.0f, 0.0f);
	        gl.glBegin(GL2.GL_LINES);
	            gl.glVertex2i(x1,y1);
	            gl.glVertex2i(x2,y2);
	        gl.glEnd();
	        gl.glFlush();
	    }
	}
	
	

		

	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl  =  drawable.getGL().getGL2();
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	    gl.glColor3f(0.0f,0.0f,0.0f);
	    gl.glBegin(GL2.GL_LINES);
	        gl.glVertex2i(x1,y3);
	        gl.glVertex2i(x2,y2);
	    gl.glEnd();
	    gl.glFlush();
	    clip(gl,x1,y3,x2,y2);
	}
	
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}





	public void init(GLAutoDrawable gld) {
		// TODO Auto-generated method stub
		 GL2 gl = gld.getGL().getGL2();
		 glu = new GLU();

		  gl.glClearColor(1.0f,1.0f,1.0f,1.0f);
		    gl.glMatrixMode(GL2.GL_PROJECTION);
		glu.gluOrtho2D(-320,320,-240,240);
	}





	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		GLProfile glp = GLProfile.get(GLProfile.GL2);
		GLCapabilities cap = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(cap);
		
		Frame frame = new Frame("Assignment1");
		frame.setSize(1200, 800);
		frame.add(canvas);
		frame.setVisible(true);
		
		Ok l = new Ok();
	    canvas.addGLEventListener(l);  
	   
	
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}});
		}
		
		
}
	
