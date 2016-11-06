package OneTriangle;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A minimal program that draws with JOGL in an AWT Frame.
 *
 * @author Wade Walker
 */
public class OneTriangleAWT {

    private static FPSAnimator animator;

    private static cube first_cube,second_cube;
    
    public static void main( String [] args ) {



        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );
        
        int widthh = 300;//(int) screenSize.getWidth();
        int heightt = 300;//(int) screenSize.getHeight();
       

        first_cube = new cube();
        second_cube =new cube();


        glcanvas.addGLEventListener( new GLEventListener() {
             int counter=1000;
            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                //OneTriangle.setup( glautodrawable.getGL().getGL2(), width, counter );
                first_cube.setup( glautodrawable.getGL().getGL2(), width, height );
                second_cube.setup( glautodrawable.getGL().getGL2(), width, height );
            }
            
            @Override
            public void init( GLAutoDrawable glautodrawable ) {
                System.out.println("init called");
                //first_cube.setup( glautodrawable.getGL().getGL2(), widthh, heightt );
            }
            
            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
                System.out.println("dispose called");
            }
            
            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                System.out.println("display called");

            
                //OneTriangle.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight());
                first_cube.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight(),3f,-10.0f );
                //second_cube.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight(),-3f,-9.0f );
            }
        });

        final Frame frame = new Frame( "One Triangle AWT" );
        
        frame.add( glcanvas );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                frame.remove( glcanvas );
                frame.dispose();
                System.exit( 0 );
            }
        });

        animator = new FPSAnimator(160);
        animator.add(glcanvas);
        animator.start();

        frame.setSize( 640, 480 );
        frame.setVisible( true );
    }
}
