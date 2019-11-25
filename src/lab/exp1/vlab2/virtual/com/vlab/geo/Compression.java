package com.vlab.geo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Formatter;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.TransparencyAttributes;

import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;

import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.util.*;

import org.omg.CORBA.Bounds;

import eerc.vlab.common.FullViewGraph;
import eerc.vlab.common.HelpWindow;
import eerc.vlab.common.HorizontalGraph;
import eerc.vlab.common.HorizontalGraphWrapper;
import eerc.vlab.common.ImagePanel;
import eerc.vlab.common.J3DShape;
import eerc.vlab.common.Resources;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Compression extends javax.swing.JPanel {
	//  Variables declaration - do not modify//GEN-BEGIN:variables
	//////////////////////////GUI componenet ///////////////////////////
	private javax.swing.JPanel topPanel;

	private javax.swing.JPanel simulationPanel;

	private javax.swing.JPanel bottomPanel;

	private javax.swing.JPanel rightPanel;

	private javax.swing.JPanel in1; // Input panel 1

	private javax.swing.JPanel in2; // Input panel 2

	private javax.swing.JPanel in3; // Input panel 3

	private boolean noTriangulate = false;

	private boolean noStripify = false;

	private double creaseAngle = 60.0;

	//	private javax.swing.JPanel rp1;			// Right Input panel 1
	//	private javax.swing.JPanel rp2;			// Right Input panel 2
	//	private javax.swing.JPanel ImagePanel;

	private javax.swing.JButton startButton = null;

	private javax.swing.JButton reStartButton = null;

	private javax.swing.JButton nextButton = null;

	private JComboBox spr_mat;

	private JSlider m_Slider[] = new JSlider[4];

	private String BOS="Type 1";
	JTable table;
	JScrollPane jsp;
	String[][] cellData;

	String[] columnNames;

	private javax.swing.JButton rightIcon = null;

	//private GraphPlotter         graphPlotter;
	////////////////////////////Java3D componenet ///////////////////////////

	private SimpleUniverse univ = null; // Simple Universe Java3D

	private BranchGroup scene = null; // BranchGroup Scene graph

	TransformGroup objSwitchPos = new TransformGroup(new Transform3D());

	TransformGroup sievePos = new TransformGroup(new Transform3D());

	TransformGroup cyl0 = new TransformGroup();

	TransformGroup cyl1 = new TransformGroup();

	TransformGroup cyl2 = new TransformGroup();

	TransformGroup cyl3 = new TransformGroup();

	TransformGroup cyl4 = new TransformGroup();

	private Switch objSwitch = new Switch();

	Appearance appea = new Appearance();

	private CompressionBody       freeBody =null;               // Shape3D


	private FullViewGraph fullViewGraph = new FullViewGraph();

	@SuppressWarnings("unchecked")
	private HashMap hm = new HashMap();

	private eerc.vlab.common.J3DShape m_j3d = new J3DShape();

	private double fields[];

	private JLabel outlbl_val[];

	private JLabel iLabel[];

	private JLabel m_Objective = new JLabel("Objective:");

	private Timer timer = null;

	private Timer m_cameraTimer = null;

	private float m_cameraViews[];

	private int m_cameraEye;

	// Timer for simulation    

	private int stage = 0;

	private int cnt = 0;

	private int state = 0;

	private float x = 0;

	private int st = 0;

	private boolean startStop = false;

	private boolean valChange = true;

	//	private JComboBox ch;
	//	private JComboBox che;
	//	private JLabel lbl_k;

	//	private String[] units ={" (m) "," (m) "," (mm) "," (Kg/m^3) ",
	//							 " (m) "," (mm) "," (mm) ","",
	//							 " (m/s) "," (mm) "," (%) "};

	public BranchGroup createSceneGraph() {
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		objRoot.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		objRoot.setCapability(Group.ALLOW_CHILDREN_READ);
		objRoot.setCapability(Group.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability(BranchGroup.ALLOW_DETACH);

		objRoot.addChild(createVirtualLab());

		//		 Floor
		int i,j;
		for(i=-4;i<=4;i++)
		{
		for(j=-4;j<=4;j++)
		{
		objRoot.addChild(m_j3d.createBox(new Vector3d((float)(i),-0.6,(float)(j)),new Vector3d(0.5,.01,0.5),new Vector3d(0,0,0),new Color3f(.8f, .8f, .8f),"resources/images/tile.jpg"));
		}
		}
		// objRoot.addChild(m_j3d.createBox(new Vector3d(0,-0.6, -.1),new Vector3d(1.5,.01,1.5),new Vector3d(0,0,0),new Color3f(.8f, .8f, .8f),"resources/images/tile.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.4,-2.5),new Vector3d(10,10,.01),new Vector3d(0f, 0f,0f), new Color3f(0.5f,0.6f,0.72f)));
		// Walls and roof
		objRoot.addChild(m_j3d.createBox(new Vector3d(1.15,0.1f,0), new Vector3d(0.05,0.7f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/377.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(-1.15,0.1f,0), new Vector3d(0.05,0.7f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/377.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.1f,-2.0), new Vector3d(1.15,0.7f,0.05),new Vector3d(0,0,0),new Color3f(1f, 1f, 0.9f),"resources/images/380.jpg"));
		objRoot.addChild(m_j3d.createBox(new Vector3d(0,0.84f,0), new Vector3d(1.15,0.04f,2),new Vector3d(0,0,0),new Color3f(1f, 1f, 1f),"resources/images/377.jpg"));

		float rad = (float) Math.PI / 180;
		Transform3D t = new Transform3D();
		t.setScale(0.4);
		t.invert();
		TransformGroup tg = new TransformGroup();
		t = new Transform3D();
		t.rotX(rad * 10);
		t.setScale(new Vector3d(.5f, .05f, .5f));
		t.setTranslation(new Vector3d(.3, .3, 0));
		tg.setTransform(t);
		freeBody = new CompressionBody();    
		Color3f light1Color = new Color3f(1f, 1f, 1f);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.20, 0.20, 0.20),
				100.0);

		Vector3f light1Direction = new Vector3f(24.0f, -7.0f, -12.0f);

		DirectionalLight light1 = new DirectionalLight(light1Color,
				light1Direction);

		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);

		AmbientLight ambientLight = new AmbientLight(new Color3f(.1f, .1f, .1f));

		ambientLight.setInfluencingBounds(bounds);

		objRoot.addChild(ambientLight);
		return objRoot;

	}

	private Canvas3D createUniverse(Container container) {
		GraphicsDevice graphicsDevice;
		if (container.getGraphicsConfiguration() != null) {
			graphicsDevice = container.getGraphicsConfiguration().getDevice();
		} else {
			graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice();
		}
		GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
		GraphicsConfiguration config = graphicsDevice
				.getBestConfiguration(template);

		Canvas3D c = new Canvas3D(config);

		univ = new SimpleUniverse(c);

		// This will move the ViewPlatform back a bit so the
		// objects in the scene can be viewed.

		//ViewingPlatform viewingPlatform = univ.getViewingPlatform();
		setLight();

		univ.getViewingPlatform().setNominalViewingTransform();

		ViewingPlatform vp = univ.getViewingPlatform();
		TransformGroup steerTG = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		steerTG.getTransform(t3d);
		Vector3d s = new Vector3d();
		Vector3f currPos = new Vector3f();
		t3d.get(currPos);

		// System.out.println("current Pos:" + currPos);

		t3d.lookAt(new Point3d(0, .2, 4), new Point3d(0, 0, 0), new Vector3d(0,
				1, 0));
		t3d.invert();

		//t3d.setTranslation(new Vector3d(0,0,8));
		steerTG.setTransform(t3d);

		// Ensure at least 5 msec per frame (i.e., < 200Hz)
		univ.getViewer().getView().setMinimumFrameCycleTime(5);

		return c;
	}

	private void setLight() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
				100.0);
		PlatformGeometry pg = new PlatformGeometry();

		Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		pg.addChild(ambientLightNode);

		Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
		Vector3f light1Direction = new Vector3f(1.0f, 1.0f, 1.0f);
		Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light2Direction = new Vector3f(-1.0f, -1.0f, -1.0f);

		DirectionalLight light1 = new DirectionalLight(light1Color,
				light1Direction);
		light1.setInfluencingBounds(bounds);
		pg.addChild(light1);

		DirectionalLight light2 = new DirectionalLight(light2Color,
				light2Direction);
		light2.setInfluencingBounds(bounds);
		pg.addChild(light2);

		ViewingPlatform viewingPlatform = univ.getViewingPlatform();
		viewingPlatform.setPlatformGeometry(pg);

	}

	private void destroy() {
		univ.cleanup();
	}

	public Group createBox(Vector3d pos, Vector3d scale, Vector3d rot,
			Color3f colr) {
		// Create a transform group node to scale and position the object.
		//new Point3d(0.0, 0.0, 0.0)
		Transform3D t = new Transform3D();
		float rad = (float) Math.PI / 180;
		if (rot.x != 0)
			t.rotX(rad * rot.x);
		else if (rot.y != 0)
			t.rotY(rad * rot.y);
		else if (rot.z != 0)
			t.rotZ(rad * rot.z);
		t.setScale(scale);
		t.setTranslation(pos);

		TransformGroup objtrans = new TransformGroup(t);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		// Create a simple shape leaf node and add it to the scene graph
		//Shape3D shape = new Box(1.0, 1.0, 1.0);       

		// Create a new ColoringAttributes object for the shape's
		// appearance and make it writable at runtime.
		Appearance app = new Appearance();
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparencyMode(TransparencyAttributes.BLENDED);
		ta.setTransparency(0.7f);
		app.setTransparencyAttributes(ta);

		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(colr);
		app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		app.setColoringAttributes(ca);

		objtrans.addChild(new Box(4.0f, 4.0f, 4.0f, app));

		return objtrans;
	}

	public Group createWireBox(Vector3d pos, Vector3d scale, Vector3d rot,
			Color3f colr) {
		// Create a transform group node to scale and position the object.
		//new Point3d(0.0, 0.0, 0.0)
		Transform3D t = new Transform3D();
		float rad = (float) Math.PI / 180;
		if (rot.x != 0)
			t.rotX(rad * rot.x);
		else if (rot.y != 0)
			t.rotY(rad * rot.y);
		else if (rot.z != 0)
			t.rotZ(rad * rot.z);
		t.setScale(scale);
		t.setTranslation(pos);

		TransformGroup objtrans = new TransformGroup(t);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		// Create a simple shape leaf node and add it to the scene graph
		//Shape3D shape = new Box(1.0, 1.0, 1.0);       

		// Create a new ColoringAttributes object for the shape's
		// appearance and make it writable at runtime.
		Appearance app = new Appearance();

		PolygonAttributes polyAttr = new PolygonAttributes();
		polyAttr.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		// polyAttr.setPolygonMode(PolygonAttributes.POLYGON_POINT);
		polyAttr.setCullFace(PolygonAttributes.CULL_NONE);
		app.setPolygonAttributes(polyAttr);

		/*LineAttributes LineAttr = new LineAttributes();
		 LineAttr.setLinePattern(LineAttributes.PATTERN_SOLID);
		 // polyAttr.setPolygonMode(PolygonAttributes.POLYGON_POINT);
		 //polyAttr.setCullFace(PolygonAttributes.CULL_NONE);
		 app.setLineAttributes(LineAttr);*/

		//ta.setTransparencyMode (TransparencyAttributes.BLENDED);
		//ta.setTransparency (0.7f);
		//app.setTransparencyAttributes (ta);
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(colr);
		app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		app.setColoringAttributes(ca);

		objtrans.addChild(new Box(1.0f, 1.0f, 1.0f, app));

		return objtrans;
	}

	public Group loadObjectFile(String objfile, Vector3d pos, Vector3d scale,
			Vector3d rot, Color3f colr) {

		//objScale.addChild(objTrans);

		int flags = ObjectFile.RESIZE;
		if (!noTriangulate)
			flags |= ObjectFile.TRIANGULATE;
		if (!noStripify)
			flags |= ObjectFile.STRIPIFY;

		ObjectFile f = new ObjectFile(flags,
				(float) (creaseAngle * Math.PI / 180.0));
		Scene s = null;
		URL filename = Resources.getResource(objfile);
		//pendulum
		try {
			s = f.load(filename);

		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (ParsingErrorException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IncorrectFormatException e) {
			System.err.println(e);
			System.exit(1);
		}

		Transform3D t = new Transform3D();
		float rad = (float) Math.PI / 180;
		if (rot.x != 0)
			t.rotX(rad * rot.x);
		else if (rot.y != 0)
			t.rotY(rad * rot.y);
		else if (rot.z != 0)
			t.rotZ(rad * rot.z);
		t.setScale(scale);
		t.setTranslation(pos);

		TransformGroup objTrans = new TransformGroup(t);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		BranchGroup bg = s.getSceneGroup();
		BoundingBox boundingBox = new BoundingBox(s.getSceneGroup().getBounds());
		Point3d lower = new Point3d();
		boundingBox.getLower(lower);

		Appearance app = new Appearance();
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparencyMode(TransparencyAttributes.BLENDED);
		ta.setTransparency(0.6f);
		app.setTransparencyAttributes(ta);

		ColoringAttributes ca = new ColoringAttributes(colr, 0);
		//ca.setColor(colr);
		app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		app.setColoringAttributes(ca);

		//	   	        Color3f objColor = m_j3d.getColor3f(184,134,11);
		Color3f objColor = m_j3d.getColor3f(160, 82, 45);
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		app.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		app.setMaterial(new Material(colr, black, objColor, black, 80.0f));
		Shape3D newShape = (Shape3D) bg.getChild(0);
		newShape.setAppearance(app);
		//	   			bg.removeChild(0);
		//	   			Appearance app = new Appearance();

		//
		//	   			
		//
		//	   			objTrans.addChild(newShape);

		//	   			Map<String, Shape3D> nameMap = s.getNamedObjects();   
		//	   			   
		//	   			for (String name : nameMap.keySet()) {  
		//	   			        System.out.printf("Name: %s\n", name);   
		//	   			} 
		//bg.addChild(trans);
		//objTrans.addChild(bg);
		objTrans.addChild(bg);

		return objTrans;
	}

	public Group createTextureCylinder(Vector3d pos, Vector3d scale,
			Vector3d rot, Color3f colr, String texfile) {
		// Create a transform group node to scale and position the object.
		//new Point3d(0.0, 0.0, 0.0)
		Transform3D t = new Transform3D();
		float rad = (float) Math.PI / 180;
		if (rot.x != 0)
			t.rotX(rad * rot.x);
		else if (rot.y != 0)
			t.rotY(rad * rot.y);
		else if (rot.z != 0)
			t.rotZ(rad * rot.z);
		t.setScale(scale);
		t.setTranslation(pos);

		TransformGroup objtrans = new TransformGroup(t);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		// Create a simple shape leaf node and add it to the scene graph
		//Shape3D shape = new Box(1.0, 1.0, 1.0);

		// Create a new ColoringAttributes object for the shape's
		// appearance and make it writable at runtime.
		Appearance app = new Appearance();
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(colr);
		app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		app.setColoringAttributes(ca);
		//objtrans.addChild(new Cylinder(0.1f,0.1f,app));

		if (texfile != null) {
			Texture tex = new TextureLoader(Resources.getResource(texfile),
					TextureLoader.BY_REFERENCE | TextureLoader.Y_UP, this)
					.getTexture();
			app.setTexture(tex);
			TextureAttributes texAttr = new TextureAttributes();
			texAttr.setTextureMode(TextureAttributes.MODULATE);
			app.setTextureAttributes(texAttr);
		}

		objtrans.addChild(new Cylinder(0.1f, 0.1f,
				Cylinder.GENERATE_TEXTURE_COORDS
						| Cylinder.GENERATE_TEXTURE_COORDS_Y_UP, app));

		return objtrans;
	}

	private Group createVirtualLab() {

		int i;

		TransformGroup box = new TransformGroup();
		TransformGroup table = new TransformGroup();

		TransformGroup obj1 = new TransformGroup();
		TransformGroup obj2 = new TransformGroup();
		TransformGroup obj3 = new TransformGroup();
		TransformGroup obj4 = new TransformGroup();
		TransformGroup obj5 = new TransformGroup();
		TransformGroup text = new TransformGroup();

	//	Transform3D grain4 = new Transform3D();
	//	Transform3D grain3 = new Transform3D();
	//	Transform3D grain2 = new Transform3D();
	//	Transform3D grain1 = new Transform3D();
	//	Transform3D grain0 = new Transform3D();
		// Transform3D zscl =new Transform3D();

	//	grain4.setTranslation(new Vector3d(0, .395 + .055, 0));
	//	grain3.setTranslation(new Vector3d(0, .26 + .055, 0));
	//	grain2.setTranslation(new Vector3d(0, .16 + .055, 0));
	//	grain1.setTranslation(new Vector3d(0, .06 + .055, 0));
	//	grain0.setTranslation(new Vector3d(0, -.075 + .055, 0));

	//	grain3.setScale(new Vector3d(0, 0, 0));
	//	grain2.setScale(new Vector3d(0, 0, 0));
	//	grain1.setScale(new Vector3d(0, 0, 0));
	//	grain0.setScale(new Vector3d(0, 0, 0));

		box.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		box.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		obj1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		obj2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		obj3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		obj4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		obj4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		obj5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		obj5.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		
		Transform3D t = new Transform3D();
		BranchGroup objroot = new BranchGroup();

		TransformGroup objtrans = new TransformGroup(t);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		//objroot.addChild(objtrans);

		float h;
		h = 0.055f;
		//final float L0 = 0.36f;
		
obj3.addChild(m_j3d.createBox(new Vector3d(-0.25f,0.22f,0.10f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
obj3.addChild(m_j3d.createBox(new Vector3d(-0.25f,-0.52f,-0.05f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
		
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.60,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.58,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
	
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.32f,.25f),new Vector3d(1.3,1.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));	
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f, 0.25f,.25f),new Vector3d(1.3,3.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/spring1.jpeg", hm));		



obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.25f,.25f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.15f,.26f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
//
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.50f,.26f),new Vector3d(1.83,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
		
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.11f,0.43f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));		
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.32f,0.43f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,20), new Color3f(0f,0f,0f)));		
obj3.addChild(m_j3d.createCylinder(new Vector3d( -0.22f,0.43f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,0), new Color3f(0f,0f,0f)));		


obj3.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.18f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj3.addChild(m_j3d.createBox(new Vector3d(-0.22f, 0.10f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
//samples
obj3.addChild(m_j3d.createBox(new Vector3d(-0.92f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con1.jpeg"));
obj3.addChild(m_j3d.createBox(new Vector3d(0.99f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con2.jpg"));
obj3.addChild(m_j3d.createBox(new Vector3d(1.45f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/om3.jpeg"));

obj3.addChild(m_j3d.createBox(new Vector3d(0.40f,-0.2f,0.49f),new Vector3d(0.19f,0.24f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));

obj3.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj3.addChild(m_j3d.createBox(new Vector3d(0.45f,-0.1f,0.90f),new Vector3d(0.05f,0.05f,0f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/guage.jpeg"));




obj3.addChild(m_j3d.createCylinder(new Vector3d(0.52f,0.10f,.95f),new Vector3d(0.3,0.32,.50),new Vector3d(12,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		

/*obj3.addChild(m_j3d.createCylinder(new Vector3d(0.51f,0.15f,0.95f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.15f,1.85f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,-50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.33f,0.15f,1.85f),new Vector3d(0.0009,0.63,.50),new Vector3d(0,0,50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
*/
/*obj3.addChild(m_j3d.createCylinder(new Vector3d(0.50f,0.21f,1.0f),new Vector3d(0.15,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));		
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.18f,1.95f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.30f,0.18f,1.85f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
*/

obj3.addChild(m_j3d.createBox(new Vector3d(-0.25f,0.22f,0.10f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
obj3.addChild(m_j3d.createBox(new Vector3d(-0.25f,-0.52f,-0.05f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
		
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.60,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.58,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
	
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.32f,.25f),new Vector3d(1.3,2.10,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj3.addChild(m_j3d.createCylinder(new Vector3d(-0.22f, 0.25f,.25f),new Vector3d(1.3,3.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/st.jpeg", hm));		

obj3.addChild(createTextureCylinder(new Vector3d(0.51f,0.13f,0.95f), new Vector3d(1.83,0.10,1.50), new Vector3d(15, 0, -360),
		m_j3d.getColor3f(238, 223, 204),
		"resources/images/ring.jpeg"));

obj2.addChild(createTextureCylinder(new Vector3d(0.51f,0.13f,0.95f), new Vector3d(1.83,0.10,1.50), new Vector3d(15, 0, 90),
		m_j3d.getColor3f(238, 223, 204),
		"resources/images/ring.jpeg"));

obj1.addChild(createTextureCylinder(new Vector3d(0.51f,0.13f,0.95f), new Vector3d(1.83,0.10,1.50), new Vector3d(15, 0, 180),
		m_j3d.getColor3f(238, 223, 204),
		"resources/images/ring.jpeg"));


obj4.addChild(createTextureCylinder(new Vector3d(0.51f,0.13f,0.95f), new Vector3d(1.83,0.10,1.50), new Vector3d(15, 0, 360),
		m_j3d.getColor3f(238, 223, 204),
		"resources/images/ring.jpeg"));










obj2.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.25f,.25f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj2.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.10f,.26f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
obj2.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.50f,.26f),new Vector3d(1.83,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
		
obj2.addChild(m_j3d.createCylinder(new Vector3d(-0.11f,0.45f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));		
obj2.addChild(m_j3d.createCylinder(new Vector3d(-0.32f,0.45f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,20), new Color3f(0f,0f,0f)));		


obj2.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.18f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj2.addChild(m_j3d.createBox(new Vector3d(-0.22f, 0.05f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
		
obj2.addChild(m_j3d.createBox(new Vector3d(0.40f,-0.2f,0.49f),new Vector3d(0.19f,0.24f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));

obj2.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj2.addChild(m_j3d.createBox(new Vector3d(0.45f,-0.1f,0.90f),new Vector3d(0.05f,0.05f,0f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/guage.jpeg"));

obj2.addChild(m_j3d.createCylinder(new Vector3d(0.52f,0.10f,.95f),new Vector3d(0.3,0.32,.50),new Vector3d(12,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
/*
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.51f,0.15f,0.95f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.15f,1.85f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,-50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.33f,0.15f,1.85f),new Vector3d(0.0009,0.63,.50),new Vector3d(0,0,50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		

obj2.addChild(m_j3d.createCylinder(new Vector3d(0.50f,0.21f,1.0f),new Vector3d(0.15,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));		
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.18f,1.95f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
obj2.addChild(m_j3d.createCylinder(new Vector3d(0.30f,0.18f,1.85f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
*/


obj1.addChild(m_j3d.createBox(new Vector3d(-0.25f,0.22f,0.10f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
obj1.addChild(m_j3d.createBox(new Vector3d(-0.25f,-0.52f,-0.05f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
		
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.60,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.58,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
	
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.32f,.25f),new Vector3d(1.3,1.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f, 0.22f,.25f),new Vector3d(1.3,3.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/st.jpeg", hm));		

//samples
obj1.addChild(m_j3d.createBox(new Vector3d(-0.92f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con1.jpeg"));
obj1.addChild(m_j3d.createBox(new Vector3d(0.99f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con2.jpg"));
//obj1.addChild(m_j3d.createBox(new Vector3d(1.45f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con3.jpeg"));


//sample
obj1.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.06f,0.25f),new Vector3d(0.1f,0.06f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/om3.jpeg"));




obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.25f,.25f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.07f,.26f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.47f,.26f),new Vector3d(1.83,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.11f,0.41f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.32f,0.41f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,20), new Color3f(0f,0f,0f)));		
obj1.addChild(m_j3d.createCylinder(new Vector3d( -0.22f,0.42f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,0), new Color3f(0f,0f,0f)));		


obj1.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.18f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj1.addChild(m_j3d.createBox(new Vector3d(-0.22f, 0.02f,0.25f),new Vector3d(0.2f,0.03f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
		
obj1.addChild(m_j3d.createBox(new Vector3d(0.40f,-0.2f,0.49f),new Vector3d(0.19f,0.24f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));

obj1.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj1.addChild(m_j3d.createBox(new Vector3d(0.45f,-0.1f,0.90f),new Vector3d(0.05f,0.05f,0f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/guage.jpeg"));

obj1.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));


obj1.addChild(m_j3d.createCylinder(new Vector3d(0.52f,0.10f,.95f),new Vector3d(0.3,0.32,.50),new Vector3d(12,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
/*
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.51f,0.15f,0.95f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.15f,1.85f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,-50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.33f,0.15f,1.85f),new Vector3d(0.0009,0.63,.50),new Vector3d(0,0,50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		

obj1.addChild(m_j3d.createCylinder(new Vector3d(0.50f,0.21f,1.0f),new Vector3d(0.15,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.18f,1.95f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.30f,0.18f,1.85f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
*/


obj4.addChild(m_j3d.createBox(new Vector3d(-0.25f,0.22f,0.10f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
obj4.addChild(m_j3d.createBox(new Vector3d(-0.25f,-0.52f,-0.05f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
		
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.60,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.58,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
	
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.32f,.25f),new Vector3d(1.3,1.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.22f, 0.25f,.25f),new Vector3d(1.3,3.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/st.jpeg", hm));		

//samples
obj4.addChild(m_j3d.createBox(new Vector3d(-0.92f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con1.jpeg"));
obj4.addChild(m_j3d.createBox(new Vector3d(0.99f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con2.jpg"));
//obj4.addChild(m_j3d.createBox(new Vector3d(1.45f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/om3.jpeg"));





obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.25f,.25f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.08f,.26f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.48f,.26f),new Vector3d(1.83,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
		
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.11f,0.43f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(-0.32f,0.43f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,20), new Color3f(0f,0f,0f)));		
obj4.addChild(m_j3d.createCylinder(new Vector3d( -0.22f,0.43f,0.05f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,0), new Color3f(0f,0f,0f)));		


obj4.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.18f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj4.addChild(m_j3d.createBox(new Vector3d(-0.22f, 0.03f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
		
obj4.addChild(m_j3d.createBox(new Vector3d(0.40f,-0.2f,0.49f),new Vector3d(0.19f,0.24f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));

obj4.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj4.addChild(m_j3d.createBox(new Vector3d(0.45f,-0.1f,0.90f),new Vector3d(0.05f,0.05f,0f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/guage.jpeg"));

obj4.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));


obj4.addChild(m_j3d.createCylinder(new Vector3d(0.52f,0.10f,.95f),new Vector3d(0.3,0.32,.50),new Vector3d(12,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
/*
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.51f,0.15f,0.95f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.15f,1.85f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,-50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.33f,0.15f,1.85f),new Vector3d(0.0009,0.63,.50),new Vector3d(0,0,50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));		

obj4.addChild(m_j3d.createCylinder(new Vector3d(0.50f,0.21f,1.0f),new Vector3d(0.15,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));		
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.18f,1.95f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.30f,0.18f,1.85f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
*/
obj1.addChild(m_j3d.createBox(new Vector3d(-0.25f,0.22f,0.10f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
obj1.addChild(m_j3d.createBox(new Vector3d(-0.25f,-0.52f,-0.05f),new Vector3d(0.4f,0.04f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));
		
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.60,-0.10f,-0.25),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.58,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
obj1.addChild(m_j3d.createCylinder(new Vector3d(0.10,-0.10f,0.47),new Vector3d(0.3,7.0f,0.3),new Vector3d(0,0,0),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));
	
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.32f,.25f),new Vector3d(1.3,1.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f, 0.22f,.25f),new Vector3d(1.3,3.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/st.jpeg", hm));		

//samples
obj1.addChild(m_j3d.createBox(new Vector3d(-0.92f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con1.jpeg"));
obj1.addChild(m_j3d.createBox(new Vector3d(0.99f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con2.jpg"));
//obj1.addChild(m_j3d.createBox(new Vector3d(1.45f,-0.52f,-0.25f),new Vector3d(0.1f,0.08f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/con3.jpeg"));


//sample
obj1.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.06f,0.25f),new Vector3d(0.1f,0.06f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/om3.jpeg"));
obj4.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.06f,0.25f),new Vector3d(0.1f,0.06f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/om3.jpeg"));




obj5.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.25f,.25f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		
obj5.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.07f,.26f),new Vector3d(1.43,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
obj5.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,0.47f,.26f),new Vector3d(1.83,0.32,1.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
		
obj5.addChild(m_j3d.createCylinder(new Vector3d(-0.11f,0.41f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));		
obj5.addChild(m_j3d.createCylinder(new Vector3d(-0.32f,0.41f,0.35f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,20), new Color3f(0f,0f,0f)));		
obj5.addChild(m_j3d.createCylinder(new Vector3d( -0.22f,0.41f,0.05f),new Vector3d(0.1,0.63,1.50),new Vector3d(0,0,0), new Color3f(0f,0f,0f)));		


obj5.addChild(m_j3d.createBox(new Vector3d(-0.22f,-0.18f,0.25f),new Vector3d(0.2f,0.04f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj5.addChild(m_j3d.createBox(new Vector3d(-0.22f, 0.02f,0.25f),new Vector3d(0.2f,0.03f,0.2f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
		
obj5.addChild(m_j3d.createBox(new Vector3d(0.40f,-0.2f,0.49f),new Vector3d(0.19f,0.24f,0.4f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg"));

obj5.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));
obj5.addChild(m_j3d.createBox(new Vector3d(0.45f,-0.1f,0.90f),new Vector3d(0.05f,0.05f,0f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/guage.jpeg"));

obj5.addChild(m_j3d.createBox(new Vector3d(0.40f,0.15f,0.52f),new Vector3d(0.25f,0.04f,0.4f), new Vector3d(12f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg"));


obj5.addChild(m_j3d.createCylinder(new Vector3d(0.52f,0.10f,.95f),new Vector3d(0.3,0.32,.50),new Vector3d(12,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/bluegem.jpg", hm));		

obj5.addChild(m_j3d.createCylinder(new Vector3d(0.51f,0.15f,0.95f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,0), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
obj5.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.15f,1.85f),new Vector3d(0.0008,0.63,.50),new Vector3d(0,0,-50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		
obj5.addChild(m_j3d.createCylinder(new Vector3d(0.33f,0.15f,1.85f),new Vector3d(0.0009,0.63,.50),new Vector3d(0,0,50), new Color3f(1.0f,1.0f,1.0f),"resources/images/grey.jpg", hm));		

obj5.addChild(m_j3d.createCylinder(new Vector3d(0.50f,0.21f,1.0f),new Vector3d(0.15,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));		
obj5.addChild(m_j3d.createCylinder(new Vector3d(0.40f,0.18f,1.95f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));
obj5.addChild(m_j3d.createCylinder(new Vector3d(0.30f,0.18f,1.85f),new Vector3d(0.10,0.13,.50),new Vector3d(50,0,0), new Color3f(0f,0f,0f)));


obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.22f,-0.06f,0.32f),new Vector3d(0.1,1.14,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));		
obj1.addChild(m_j3d.createCylinder(new Vector3d(-0.21f,-0.06f,0.32f),new Vector3d(0.1,1.14,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));

obj1.addChild(m_j3d.createCylinder(new Vector3d(0.20f,-0.52f,-0.10f),new Vector3d(0.1,2.17,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));
obj3.addChild(m_j3d.createCylinder(new Vector3d(0.20f,-0.52f,-0.10f),new Vector3d(0.1,2.17,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));
obj4.addChild(m_j3d.createCylinder(new Vector3d(0.20f,-0.52f,-0.10f),new Vector3d(0.1,2.17,1.50),new Vector3d(0,0,-20), new Color3f(0f,0f,0f)));






/*	obj3.addChild(loadObjectFile("resources/geometry/Mycontainer.obj",
				new Vector3d(0.0f, .2f + h, 1f), new Vector3d(0.3, .26, 0.3),
				new Vector3d(0, 0, 0), m_j3d.getColor3f(139, 117, 0)));
		obj3.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, -0.08f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));

		//lowest sieve
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,-.015f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj3.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.03f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj3.addChild(createTextureCylinder(new Vector3d(.001f, 0.035f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//lowest but one
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.095f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj3.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.14f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj3.addChild(createTextureCylinder(new Vector3d(.001f, 0.145f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//2nd frm last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.205f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj3.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.25f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj3.addChild(createTextureCylinder(new Vector3d(.001f, 0.255f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//3rd from last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.315f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj3.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.36f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj3.addChild(createTextureCylinder(new Vector3d(.001f, 0.365f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//4th frm last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.425f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj3.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.47f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		// obj1.addChild(createTextureCylinder(new Vector3d(.001f,0.475f,.92f),new Vector3d(1.33,.09,2.35),new Vector3d(0,0,0), m_j3d.getColor3f(238,223,204),"resources/images/Wire_Mesh.jpg"));

		//sand on the top

		// obj3.addChild(createTextureCylinder(new Vector3d(.001f,0.372+h,.92f),new Vector3d(1.32,.3,2.32),new Vector3d(0,0,0), m_j3d.getColor3f(238,223,204),"resources/images/sand.jpg"));

		//support

		obj3.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(0.18,
				0.19f + h, .8), new Vector3d(.1, 6, .1), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));
		obj3.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(-0.18,
				0.19f + h, .8), new Vector3d(.1, 6, .1), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));

		//side support
		obj3.addChild(m_j3d.createCylinderWithMatProp(
				new Vector3d(0.18, .5, .8), new Vector3d(.1, 1, .1),
				new Vector3d(0, 0, 90), m_j3d.getColor3f(80, 91, 196), m_j3d
						.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));
		obj3.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(-0.18, .5,
				.8), new Vector3d(.1, 1, .1), new Vector3d(0, 0, 90), m_j3d
				.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196), m_j3d
				.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));

		//base
		obj3.addChild(createWireBox(new Vector3d(0, -.08f, .8), new Vector3d(
				0.2f, .03f, 0.2f), new Vector3d(2f, 0f, 0f), m_j3d.getColor3f(
				160, 82, 45)));
		obj3.addChild(m_j3d.createBox(new Vector3d(0, -.08f, .8), new Vector3d(
				0.2f, .03f, 0.2f), new Vector3d(2f, 0f, 0f), m_j3d.getColor3f(
				188, 143, 143)));

		objtrans.addChild(m_j3d.createBox(new Vector3d(-.25f, -.11, .8),
				new Vector3d(0.05f, .005f, 0.05f), new Vector3d(2f, 0f, 0f),
				new Color3f(.85f, 0.64f, .125f)));
		objtrans.addChild(m_j3d.createBox(new Vector3d(.25f, -.11, .8),
				new Vector3d(0.05f, .005f, 0.05f), new Vector3d(2f, 0f, 0f),
				new Color3f(.85f, 0.64f, .125f)));

		obj2.addChild(loadObjectFile("resources/geometry/Mycontainer.obj",
				new Vector3d(0.0f, .2f + h, 1f), new Vector3d(0.3, .26, 0.3),
				new Vector3d(0, 0, 0), m_j3d.getColor3f(139, 117, 0)));
		obj2.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, -0.08f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));

		//lowest sieve
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,-.015f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj2.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.03f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj2.addChild(createTextureCylinder(new Vector3d(.001f, 0.035f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//lowest but one
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.095f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj2.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.14f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj2.addChild(createTextureCylinder(new Vector3d(.001f, 0.145f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//2nd frm last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.205f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj2.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.25f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj2.addChild(createTextureCylinder(new Vector3d(.001f, 0.255f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//3rd from last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.315f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj2.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.36f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj2.addChild(createTextureCylinder(new Vector3d(.001f, 0.365f + h,
				.92f), new Vector3d(1.33, .09, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//4th frm last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.425f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj2.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.47f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		// obj1.addChild(createTextureCylinder(new Vector3d(.001f,0.475f,.92f),new Vector3d(1.33,.09,2.35),new Vector3d(0,0,0), m_j3d.getColor3f(238,223,204),"resources/images/Wire_Mesh.jpg"));

		//sand on the top

		obj2.addChild(createTextureCylinder(
				new Vector3d(.001f, 0.395 + h, .92f), new Vector3d(1.25, .7,
						1.95), new Vector3d(0, 0, 0), m_j3d.getColor3f(238,
						223, 204), "resources/images/sand.jpg"));

		//support

		obj2.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(0.18,
				0.19f + h, .8), new Vector3d(.1, 6, .1), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));
		obj2.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(-0.18,
				0.19f + h, .8), new Vector3d(.1, 6, .1), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));

		//side support
		obj2.addChild(m_j3d.createCylinderWithMatProp(
				new Vector3d(0.18, .5, .8), new Vector3d(.1, 1, .1),
				new Vector3d(0, 0, 90), m_j3d.getColor3f(80, 91, 196), m_j3d
						.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));
		obj2.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(-0.18, .5,
				.8), new Vector3d(.1, 1, .1), new Vector3d(0, 0, 90), m_j3d
				.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196), m_j3d
				.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));

		//base
		obj2.addChild(createWireBox(new Vector3d(0, -.08f, .8), new Vector3d(
				0.2f, .03f, 0.2f), new Vector3d(2f, 0f, 0f), m_j3d.getColor3f(
				160, 82, 45)));
		obj2.addChild(m_j3d.createBox(new Vector3d(0, -.08f, .8), new Vector3d(
				0.2f, .03f, 0.2f), new Vector3d(2f, 0f, 0f), m_j3d.getColor3f(
				188, 143, 143)));

		obj1.addChild(loadObjectFile("resources/geometry/Mycontainer.obj",
				new Vector3d(0.0f, .2f + h, 1f), new Vector3d(0.3, .26, 0.3),
				new Vector3d(0, 0, 0), m_j3d.getColor3f(139, 117, 0)));
		obj1.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, -0.08f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));

		//lowest sieve
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,-.015f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj1.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.03f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj1.addChild(createTextureCylinder(new Vector3d(.001f, 0.035f + h,
				.92f), new Vector3d(1.33, .07, 2.35), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//lowest but one
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.095f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj1.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.14f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj1.addChild(createTextureCylinder(new Vector3d(.001f, 0.145f + h,
				.92f), new Vector3d(1.3, .05, 2.3), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//2nd frm last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.205f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj1.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.25f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj1.addChild(createTextureCylinder(new Vector3d(.001f, 0.255f + h,
				.92f), new Vector3d(1.3, .05, 2.3), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//3rd from last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.315f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj1.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.36f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));
		obj1.addChild(createTextureCylinder(new Vector3d(.001f, 0.365f + h,
				.92f), new Vector3d(1.3, .05, 2.3), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(238, 223, 204),
				"resources/images/Wire_Mesh.jpg"));

		//4th frm last
		//obj3.addChild(m_j3d.createCylinder(new Vector3d(.001f,.425f+h,.92f),new Vector3d(1.25,.9,1.95),new Vector3d(0,0,0), m_j3d.getColor3f(168,34,34)));
		obj1.addChild(m_j3d.createCylinder(
				new Vector3d(.001f, 0.47f + h, .92f), new Vector3d(1.33, .095,
						2.35), new Vector3d(0, 0, 0), m_j3d.getColor3f(218,
						165, 32)));*/
		// obj1.addChild(createTextureCylinder(new Vector3d(.001f,0.475f,.92f),new Vector3d(1.33,.09,2.35),new Vector3d(0,0,0), m_j3d.getColor3f(238,223,204),"resources/images/Wire_Mesh.jpg"));

		//sand on the top

	/*	cyl4
				.addChild(createTextureCylinder(new Vector3d(.001f, 0, .92f),
						new Vector3d(1.25, .7, 1.95), new Vector3d(0, 0, 0),
						m_j3d.getColor3f(238, 223, 204),
						"resources/images/sand.jpg"));
		cyl3
				.addChild(createTextureCylinder(new Vector3d(.001f, 0, .92f),
						new Vector3d(1.25, .01, 1.95), new Vector3d(0, 0, 0),
						m_j3d.getColor3f(238, 223, 204),
						"resources/images/sand.jpg"));
		cyl2
				.addChild(createTextureCylinder(new Vector3d(.001f, 0, .92f),
						new Vector3d(1.25, .01, 1.95), new Vector3d(0, 0, 0),
						m_j3d.getColor3f(238, 223, 204),
						"resources/images/sand.jpg"));
		cyl1
				.addChild(createTextureCylinder(new Vector3d(.001f, 0, .92f),
						new Vector3d(1.25, .01, 1.95), new Vector3d(0, 0, 0),
						m_j3d.getColor3f(238, 223, 204),
						"resources/images/sand.jpg"));
		cyl0
				.addChild(createTextureCylinder(new Vector3d(.001f, 0, .92f),
						new Vector3d(1.25, .01, 1.95), new Vector3d(0, 0, 0),
						m_j3d.getColor3f(238, 223, 204),
						"resources/images/sand.jpg"));*/

		//side support
	/*	obj1.addChild(m_j3d.createCylinderWithMatProp(
				new Vector3d(0.18, .5, .8), new Vector3d(.1, 1, .1),
				new Vector3d(0, 0, 90), m_j3d.getColor3f(80, 91, 196), m_j3d
						.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));
		obj1.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(-0.18, .5,
				.8), new Vector3d(.1, 1, .1), new Vector3d(0, 0, 90), m_j3d
				.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196), m_j3d
				.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));*/

		//sievePos.addChild(obj1);
		sievePos.addChild(cyl0);
		sievePos.addChild(cyl1);
		sievePos.addChild(cyl2);
		sievePos.addChild(cyl3);
		sievePos.addChild(cyl4);

	//	cyl4.setTransform(grain4);
	//	cyl3.setTransform(grain3);
	//	cyl2.setTransform(grain2);
	//	cyl1.setTransform(grain1);
	//	cyl0.setTransform(grain0);

		//support

	/*	objtrans.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(0.18,
				0.19f + h, .8), new Vector3d(.1, 6, .1), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));
		objtrans.addChild(m_j3d.createCylinderWithMatProp(new Vector3d(-0.18,
				0.19f + h, .8), new Vector3d(.1, 6, .1), new Vector3d(0, 0, 0),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				m_j3d.getColor3f(80, 91, 196), m_j3d.getColor3f(80, 91, 196),
				new Color3f(0.3f, 0.3f, 0.3f), 30.0f));

		//base
		objtrans.addChild(createWireBox(new Vector3d(0, -.08f, .8),
				new Vector3d(0.2f, .03f, 0.2f), new Vector3d(2f, 0f, 0f), m_j3d
						.getColor3f(160, 82, 45)));
		objtrans.addChild(m_j3d.createBox(new Vector3d(0, -.08f, .8),
				new Vector3d(0.2f, .03f, 0.2f), new Vector3d(2f, 0f, 0f), m_j3d
						.getColor3f(188, 143, 143)));

		objtrans.addChild(m_j3d.createBox(new Vector3d(-.25f, -.11, .8),
				new Vector3d(0.05f, .005f, 0.05f), new Vector3d(2f, 0f, 0f),
				new Color3f(.85f, 0.64f, .125f)));
		objtrans.addChild(m_j3d.createBox(new Vector3d(.25f, -.11, .8),
				new Vector3d(0.05f, .005f, 0.05f), new Vector3d(2f, 0f, 0f),
				new Color3f(.85f, 0.64f, .125f)));*/

		//table
	/*	table.addChild(m_j3d.createBox(new Vector3d(0.0f, -0.2f, 0.0f),
				new Vector3d(0.4f, 0.02f, 0.4f),
				new Vector3d(0.0f, 0.0f, 0.0f), new Color3f(1.0f, 1.0f, 1.0f),
				"resources/images/wood2.jpg"));
		table.addChild(m_j3d.createCylinder(new Vector3d(0.35, -0.35f, -0.35),
				new Vector3d(0.3, 3.0f, 0.3), new Vector3d(0, 0, 0),
				new Color3f(0.4f, 0.2f, 0.0f)));
		table.addChild(m_j3d.createCylinder(new Vector3d(-0.35, -0.35f, -0.35),
				new Vector3d(0.3, 3.0f, 0.3), new Vector3d(0, 0, 0),
				new Color3f(0.4f, 0.2f, 0.0f)));
		table.addChild(m_j3d.createCylinder(new Vector3d(-0.35, -0.35f, 0.35),
				new Vector3d(0.3, 3.0f, 0.3), new Vector3d(0, 0, 0),
				new Color3f(0.4f, 0.2f, 0.0f)));
		table.addChild(m_j3d.createCylinder(new Vector3d(0.35, -0.35f, 0.35),
				new Vector3d(0.3, 3.0f, 0.3), new Vector3d(0, 0, 0),
				new Color3f(0.4f, 0.2f, 0.0f)));*/
		

		
		
		//obj3.addChild(m_j3d.createBox(new Vector3d(0.0f,(L0-H)-0.1f,0),new Vector3d(0.05f,0.04f,0.05f), new Vector3d(0.0f,0.0f,0.0f),new Color3f(1.0f,1.0f,1.0f),"resources/images/table.jpg"));

		//


objSwitch = new Switch(Switch.CHILD_MASK);
objSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);

// create switch value interpolator
// if(stage==-1)
objroot.addChild(table);      
objroot.addChild(objSwitchPos);        
/*objSwitch.addChild(obj3);
objSwitch.addChild(obj1);
objSwitch.addChild(obj2);*/



objSwitch.insertChild(obj3, 0);
objSwitch.insertChild(obj4, 1);
objSwitch.insertChild(obj2, 2);
objSwitch.insertChild(obj1,3);
objSwitch.insertChild(obj5,4);

//objSwitch.addChild(spring4);
//objSwitch.addChild(spring5);
//objSwitch.addChild(spring6);
//objSwitch.addChild(spring7);
objSwitchPos.addChild(objSwitch);       

//objroot.addChild(swiInt);
//swiInt.setLastChildIndex(6);       
return objroot;
}  

	/**
	 * Creates new form FreeVibration
	 */
	public Compression(Container container) {
		// Initialize the GUI components
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		initComponents();

		centerPanel(container);
		// Create Canvas3D and SimpleUniverse; add canvas to drawing panel

		//        scene.addChild(bgleg);
	}

	// ----------------------------------------------------------------

	// Applet framework

	public static class MyApplet extends JApplet {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Compression mainPanel;

		public void init() {
			setLayout(new BorderLayout());
			mainPanel = new Compression(this);
			add(mainPanel, BorderLayout.CENTER);

		}

		public void destroy() {
			mainPanel.destroy();
		}
	}

	// Application framework

	private static class MyFrame extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MyFrame() {
			setLayout(new BorderLayout());
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("Compression test Applet");
			getContentPane().add(new Compression(this), BorderLayout.CENTER);
			pack();
		}
	}

	// Create a form with the specified labels, tooltips, and sizes.
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MyFrame().setVisible(true);
			}
		});
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		cyl0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl0.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		cyl1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		cyl2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		cyl3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		cyl4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		cyl4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		sievePos.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objSwitchPos.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		appea.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appea.setCapability(Appearance.ALLOW_LINE_ATTRIBUTES_WRITE);
		ColoringAttributes ca = new ColoringAttributes(new Color3f(0, 0, 0),
				ColoringAttributes.SHADE_FLAT);
		appea.setColoringAttributes(ca);
		//      new GridLayout(2, 1)
		setLayout(new java.awt.BorderLayout());

		bottomPanel = new javax.swing.JPanel(); // input from user at bottom
		simulationPanel = new javax.swing.JPanel(); // 3D rendering at center
		topPanel = new javax.swing.JPanel(); // Pause, resume, Next
		rightPanel = new javax.swing.JPanel(); // Graph and Input and Output Parameter

		topPanel();
		bottomPanel();
		rightPanel();

		//      Set Alignment
		//add(guiPanel, java.awt.BorderLayout.NORTH);
		add(topPanel, java.awt.BorderLayout.NORTH);
		add(simulationPanel, java.awt.BorderLayout.CENTER);
		add(bottomPanel, java.awt.BorderLayout.SOUTH);
		add(rightPanel, java.awt.BorderLayout.EAST);

		startStop = false;
		valChange = true;

		timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//...Perform a task...
				timerActionPerformed(evt);

			}
		});

	}// </editor-fold>//GEN-END:initComponents

	private void topPanel() {

		java.awt.GridBagConstraints gridBagConstraints;

		javax.swing.JPanel guiPanel = new javax.swing.JPanel(); // Pause, resume at top
		guiPanel.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);

		//        javax.swing.JButton pauseButton = new javax.swing.JButton();  
		//        javax.swing.JButton startButton = new javax.swing.JButton(); 
		reStartButton = new javax.swing.JButton("Re-Start");
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/restart.png");
		reStartButton.setIcon(icon);
		startButton = new javax.swing.JButton("Start");
		icon = m_j3d.createImageIcon("resources/icons/start.png");
		startButton.setIcon(icon);
		nextButton = new javax.swing.JButton("Next");
		icon = m_j3d.createImageIcon("resources/icons/next.png");
		nextButton.setIcon(icon);
		//        ImageIcon icon = m_j3d.createImageIcon("resources/images/show_graph.png");        
		//        startButton.setIcon(icon);
		//startButton.setPreferredSize(new Dimension(100,30));

		//reStartButton.setText("Re-Start");  
		reStartButton.setEnabled(true);
		nextButton.setEnabled(true);

		guiPanel.setBackground(new Color(67, 143, 205));//Color.BLACK
		topPanel.setLayout(new java.awt.BorderLayout());
		topPanel.add(guiPanel, java.awt.BorderLayout.NORTH);

		startButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Toggle
				stage = 0;
				startStop = !startStop;

				if (startStop)
					startSimulation(evt);
				else
					pauseSimulation();

				//univ.getCanvas().repaint();
			}
		});

		reStartButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reStartButton.setEnabled(true);
				startButton.setEnabled(true);
				startButton.setText("Start");
				startStop = !startStop;
				 startStop = true;

			//	outputGraph.clearGraphValue();
				//    outputGraph2.clearGraphValue();

				valChange = true;
				startSimulation(evt);
				univ.getCanvas().repaint();

				// \\           	reStartButton.setEnabled(false);
				//                //startButton.setEnabled(true);
				//                startButton.setText("Start");
				             startStop = false;
				                timer.stop();
				//                outputGraph.clearGraphValue();
				//                outputGraph2.clearGraphValue();
				//                
				                valChange = true;

			}
		});

		nextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cnt++;
				if (cnt == 2)
					stage = 0;
				st++;
				if (cnt == 2) {
					nextButton.setEnabled(false);
					st--;
				} else
					nextButton.setEnabled(true);
				onNextStage();
			//	univ.getCanvas().repaint();
			}
		});

		javax.swing.JButton btn = new javax.swing.JButton("Full View Graph");
//	//	guiPanel.add(btn, gridBagConstraints);
//		icon = m_j3d.createImageIcon("resources/icons/graph_window.png");
//		btn.setIcon(icon);
//		btn.addActionListener(new java.awt.event.ActionListener() {
//			@SuppressWarnings("static-access")
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//
//				HorizontalGraph graph[] = { outputGraph };
//				int max[] = { 1000, 100 };
//				int magX[] = { 2, 2 };
//				int magY[] = { 2, 2 };
//
//				JFrame frame = new JFrame("Full View Graph");
//				//GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//				//Add contents to the window.
//
//				//frame.add(p);
//				frame.setExtendedState(frame.getExtendedState()
//						| frame.MAXIMIZED_BOTH);
//
//				//frame.setMaximizedBounds(e.getMaximumWindowBounds());
//				//frame.setSize(e.getMaximumWindowBounds().width, e.getMaximumWindowBounds().height);
//
//				//Display the window.
//				frame.pack();
//				frame.setVisible(true);
//				//frame.setResizable(false);
//
//				fullViewGraph = new FullViewGraph(graph, max, magX, magY, frame
//						.getWidth() - 20, frame.getHeight());
//				frame.add(fullViewGraph); //Pradeep: added
//				//     System.out.println("w " + frame.getWidth() + " h " + frame.getHeight());
//
//			}
//		});
//
		guiPanel.add(reStartButton, gridBagConstraints);
		guiPanel.add(startButton, gridBagConstraints);
		guiPanel.add(nextButton, gridBagConstraints);

		btn = new javax.swing.JButton("Manual");
		icon = m_j3d.createImageIcon("resources/icons/manual.png");
		btn.setIcon(icon);
		//startButton.setPreferredSize(new Dimension(100,30));
		//     guiPanel.add(btn, gridBagConstraints);
		btn.setVisible(false); //Pradeep: they said to remove Manual

		btn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				HelpWindow.createAndShowGUI("forcedVib");
			}
		});

	}

	private void rightPanel() {

		rightPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 1));
		//	 rightPanel.setPreferredSize(new java.awt.Dimension(600, 200));
		rightPanel.setBackground(Color.red);
		
		cellData = new String[10][5];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 5; j++)
				cellData[i][j] = " ";

      //   cellData=freeBody.init(BOS,(int)fields[1]);
		columnNames = new String[5];
		columnNames[0] = "Sieve Size(mm)";
		columnNames[1] = "Mass of Soil Retained(gms)";
		columnNames[2] = "% of soil retained(in %)";
		columnNames[3] = "Cumulative % of soil Retained(%)";
		columnNames[4] = "% finer=(100-p)";

		table = new JTable(cellData, columnNames);
		jsp = new JScrollPane(table);

		rightPanel.add(jsp);

		ImageIcon icon = m_j3d.createImageIcon("resources/Sieve/Type1-1/Img.jpg");
		rightIcon = new javax.swing.JButton(" ");
		rightIcon.setIcon(icon);
		//panel.add(rightIcon);
		rightPanel.add(rightIcon);

		rightPanel.setVisible(false);

	}

	private static void enable(Container root, boolean enable) {
		Component children[] = root.getComponents();
		for (int i = 0; i < children.length; i++)
			children[i].setEnabled(enable);
	}

	private void centerPanel(Container container) {

		simulationPanel.setPreferredSize(new java.awt.Dimension(1024, 600));
		simulationPanel.setLayout(new java.awt.BorderLayout());

		javax.swing.JPanel guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100, 100, 100));
		JLabel lbl = new JLabel("Compression Test", JLabel.CENTER);
		lbl.setFont(new Font("Arial", Font.BOLD, 18));

		lbl.setForeground(Color.orange);
		//lbl.setBackground(Color.BLACK);
		guiPanel.add(lbl);
		simulationPanel.add(guiPanel, BorderLayout.NORTH);

		Canvas3D c = createUniverse(container);
		simulationPanel.add(c, BorderLayout.CENTER);

		JPanel btmPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
		simulationPanel.add(btmPanel, BorderLayout.SOUTH);

		guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100, 100, 100));
		simulationPanel.add(guiPanel, BorderLayout.EAST);

		guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100, 100, 100));
		simulationPanel.add(guiPanel, BorderLayout.WEST);

		// Create the content branch and add it to the universe
		scene = createSceneGraph();
		univ.addBranchGraph(scene);

		m_Objective = new JLabel(">: Click on Next to do the Experiment",
				JLabel.LEFT);
		m_Objective.setFont(new Font("Arial", Font.BOLD, 13));
		m_Objective.setForeground(Color.WHITE);
		guiPanel = new javax.swing.JPanel();
		guiPanel.setBackground(new Color(100, 100, 100));
		guiPanel.add(m_Objective);
		btmPanel.add(guiPanel, BorderLayout.NORTH);

		guiPanel = new javax.swing.JPanel(); //          
		guiPanel.setBackground(new Color(235, 233, 215));
		guiPanel.setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);

		javax.swing.JButton viewButton = new javax.swing.JButton(
				"Horizontal View");
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/h-view.png");
		viewButton.setIcon(icon);
		viewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				//  0 , 
				if (m_cameraTimer != null && m_cameraTimer.isRunning()) {
					m_cameraTimer.stop();
				}
				setCameraViews();
				m_cameraTimer = new Timer(200, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//...Perform a task...
						timerActionHorizontalCameraMotion(evt);
					}
				});
				m_cameraTimer.start();
			}
		});

		guiPanel.add(viewButton, gridBagConstraints);

		viewButton = new javax.swing.JButton("Vertical View");
		icon = m_j3d.createImageIcon("resources/icons/v-view.png");
		viewButton.setIcon(icon);
		viewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (m_cameraTimer != null && m_cameraTimer.isRunning()) {
					m_cameraTimer.stop();
				}
				setCameraViews();
				m_cameraTimer = new Timer(200, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//...Perform a task...
						timerActionVerticalCameraMotion(evt);
					}
				});
				m_cameraTimer.start();

			}
		});

		guiPanel.add(viewButton, gridBagConstraints);

		JCheckBox chkbox = new JCheckBox("");
		lbl = new JLabel("Change Input Parameters", JLabel.CENTER);
		//lbl.setFont(new Font("Arial", Font.BOLD, 18));
		icon = m_j3d.createImageIcon("resources/icons/tasklist.png");
		lbl.setIcon(icon);
		chkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean bChecked = ((JCheckBox) event.getSource()).isSelected();
				if (bChecked)
					bottomPanel.setVisible(true);
				else
					bottomPanel.setVisible(false);
				univ.getCanvas().repaint();
				// (a) checkbox.isSelected();
				// (b) ((JCheckBox)event.getSource()).isSelected();

			}
		});

		guiPanel.add(chkbox, gridBagConstraints);
		guiPanel.add(lbl, gridBagConstraints);

		chkbox = new JCheckBox("");
		lbl = new JLabel("Show Results", JLabel.CENTER);
		//lbl.setFont(new Font("Arial", Font.BOLD, 18));
		icon = m_j3d.createImageIcon("resources/icons/show_graph.png");
		lbl.setIcon(icon);
		chkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean bChecked = ((JCheckBox) event.getSource()).isSelected();
				if (bChecked)
					rightPanel.setVisible(true);
				else
					rightPanel.setVisible(false);
				univ.getCanvas().repaint();

			}
		});
		guiPanel.add(chkbox, gridBagConstraints);
		guiPanel.add(lbl, gridBagConstraints);

		btmPanel.add(guiPanel, BorderLayout.CENTER);

		guiPanel = new javax.swing.JPanel(); // 
		guiPanel.setBackground(new Color(130, 169, 193));
		guiPanel.setBorder(BorderFactory.createLineBorder(new Color(235, 233,
				215), 4));
		//         guiPanel.add(createInputOutputPanel());
		//         btmPanel.add(guiPanel,BorderLayout.SOUTH);

	}

	

	private void bottomPanel() {
		initInputControlsField();

		Color bk = new Color(219, 226, 238);
		bottomPanel.setLayout(new java.awt.GridLayout(1, 3));
		bottomPanel.setBackground(Color.black);
		bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(235,
				233, 215), 8));

		in1 = new JPanel(new java.awt.GridLayout(2, 3));
		in1.setBackground(bk);
		bottomPanel.add(in1);

		in2 = new JPanel(new java.awt.GridLayout(2, 2));
		in2.setBackground(bk);

		bottomPanel.add(in2);

		in3 = new JPanel(new java.awt.GridLayout(2, 2));
		in3.setBackground(bk);
		bottomPanel.add(in3);

		JLabel lab = new JLabel("Type of soil    ", JLabel.LEFT);
		// lab.setForeground(Color.white);
		String[] Bstr = new String[4];
		Bstr[0] = "Type 1";
		Bstr[1] = "Type 2";
		Bstr[2] = "Type 3";
	//	Bstr[3] = "Type 4";
		JComboBox BehaviourOfString = new JComboBox(Bstr);
		BehaviourOfString
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						valChange = true;
						JComboBox cb = (JComboBox) e.getSource();
						BOS = (String) cb.getSelectedItem();
						valChange = true;

						iLabel[0].setText(" 100 ");
						// iLabel[0].setText(":: " + BOS + " ");

						repaint();

					}
				});
		in1.add(lab);
		in1.add(BehaviourOfString);
		in1.add(iLabel[0]);
		iLabel[0].setForeground(Color.BLUE);
		JLabel lab1 = new JLabel("        ", JLabel.LEFT);
		in1.add(lab1);

		lab = new JLabel("Mass of Soil ", JLabel.LEFT);
		m_Slider[0] = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);
		m_Slider[0].setMajorTickSpacing(1);
		m_Slider[0].setPaintTicks(true);
		m_Slider[0].setPaintLabels(true);
		m_Slider[0].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				valChange = true;
				int val = ((JSlider) e.getSource()).getValue();
				fields[1] = val;

				iLabel[0].setText(":: " + fields[1] * 500 + " gms");
				fields[1]=1;
				repaint();
			}
		});
		m_Slider[0].setBackground(bk);
		in1.add(lab);
		in1.add(m_Slider[0]);
		in1.add(iLabel[0]);

		outlbl_val = new JLabel[3];
		lab = new JLabel("Coefficient of curvature ", JLabel.RIGHT);
		outlbl_val[0] = new JLabel(" 0 ", JLabel.RIGHT);
		in3.add(lab);
		in3.add(outlbl_val[0]);

		lab = new JLabel("Uniformity Coefficient ", JLabel.RIGHT);
		outlbl_val[1] = new JLabel(" 0 ", JLabel.RIGHT);
		in3.add(lab);
		in3.add(outlbl_val[1]);

		bottomPanel.setVisible(false);
	
		enable(in1, false);
		enable(in3, false);
	}
   private String getDataFile(String type,int i)
   {
	   if(type.equals("Type 1") && i==1)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type1-1/Img.jpg"));
		   return "Type1-1";
	   }
	   if(type.equals("Type 1") && i==2)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type1-1/Img.jpg"));
		   return "Type1-2";
	   }
	   if(type.equals("Type 1") && i==3)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type1-1/Img.jpg"));
		   return "Type1-3";
	   }
	   if(type.equals("Type 1") && i==4)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type1-1/Img.jpg"));
		   return "Type1-4";
	   }
	   
	   if(type.equals("Type 2") && i==1)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type2-1/Img.jpg"));
		   return "Type2-1";
	   }
		   
	   if(type.equals("Type 2") && i==2)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type2-2/Img.jpg"));
		   return "Type2-2";
	   }
	   if(type.equals("Type 2") && i==3)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type2-3/Img.jpg"));
		   return "Type2-3";
	   }
		   
	   if(type.equals("Type 2") && i==4)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type2-4/Img.jpg"));
		   return "Type2-4";
	   }	
	   
	   if(type.equals("Type 3") && i==1)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type3-1/Img.jpg"));
		   return "Type3-1";
	   }
	   if(type.equals("Type 3") && i==2)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type3-2/Img.jpg"));
		   return "Type3-2";
	   }
		   
	   if(type.equals("Type 3") && i==3)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type3-3/Img.jpg"));
		   return "Type3-3";
	   }
		   
	   if(type.equals("Type 3") && i==4)
	   {
		   rightIcon.setIcon(m_j3d.createImageIcon("resources/Sieve/Type3-4/Img.jpg"));
		   return "Type3-4";
	   }
	   return "Type1-1";
   }

	private void initInputControlsField() {

		iLabel = new JLabel[5];
		int i = 0;
		iLabel[i] = new JLabel(" 500 gms ", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);
		iLabel[i] = new JLabel(" 500 gms ", JLabel.LEFT);
		iLabel[i++].setForeground(Color.blue);
		i = 0;
		fields = new double[5];
		fields[0] = 500.0;
		fields[1] = 1.0;
	
	}

	private void onNextStage() {
	/*	st = st % 5;
		if (st == 1)
			objSwitch.setWhichChild(1);
		if (st == 2)
			objSwitch.setWhichChild(2);
		if(st==3)
		 objSwitch.setWhichChild(3);

		if(st==0)
			 objSwitch.setWhichChild(1);*/

		
		
		valChange = true; // Clear the graph. or Graph will restart on Play    	
		resetOutputParameters(); // Clear the Output Parameters
		//bottomPanel.setVisible(true);
		enableStage(st);
		setInstructionText();

	}

	private void enableStage(int s) {
		switch (s) {
		case 0: // Home     		
			enable(in1, true);
			enable(in2, true);
			enable(in3, true);

			break;
		case 1: // Home 

			enable(in1, true);
			enable(in2, true);
			enable(in3, true);

			break;
		case 2:

			enable(in1, true);
			enable(in2, true);
			enable(in3, true);
			break;

		case 3:

			enable(in1, true);
			enable(in2, true);
			enable(in3, true);
			break;
		//    	case 2:
		//
		//    		enable(in1,false);	 enable(in2,false);		 enable(in3,true);	
		//    		break;
		//			
		//    	case 3:
		//    		enable(in1,true);	 enable(in2,true);		 enable(in3,true);	
		//		break;

		}

	}

	private void setInstructionText() {

		valChange = true;     	
		resetOutputParameters();

		switch (stage) {
		case 0: // Home 
			m_Objective
					.setText(">: Click Next");
			m_Objective.setForeground(Color.WHITE);
			break;
		case 1:
			m_Objective
					.setText(">: Click Start");
			m_Objective.setForeground(Color.GREEN);
			break;

		}

	}

	private void resetOutputParameters() {
		//    	int i=0;
		//    	outlbl_val[i++].setText(getMass() + " Kg");
		//        outlbl_val[i++].setText(String.valueOf(getStiff()).substring(0,4)+ String.valueOf(getStiff()).substring(String.valueOf(getStiff()).length()-4,String.valueOf(getStiff()).length())+" N/m");
		//    	 i=2;
		//        outlbl_val[i++].setText(" t sec");
		//        outlbl_val[i++].setText(" d m");
		//       

	}

	private void setCameraViews() {
		m_cameraViews = new float[360];
		int i = 0;
		for (i = 0; i < 90; i++)
			m_cameraViews[i] = i;
		for (int j = 0; j < 90; j++, i++)
			m_cameraViews[i] = (90 - j);
		for (int j = 0; j < 90; j++, i++)
			m_cameraViews[i] = -j;
		for (int j = 0; j < 90; j++, i++)
			m_cameraViews[i] = -(90 - j);

		m_cameraEye = 0;

	}

	private void timerActionVerticalCameraMotion(java.awt.event.ActionEvent evt) {
		ViewingPlatform vp = univ.getViewingPlatform();
		TransformGroup steerTG = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		steerTG.getTransform(t3d);
		//Vector3d s = new Vector3d();
		Vector3f currPos = new Vector3f();
		t3d.get(currPos);

		// System.out.println("current Pos:" + currPos);
		/*   float y = (float)(float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye]));
		 float z = 2.41f - Math.abs(y);//((float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye])));
		 // default (0, 0, 2.41)
		 // System.out.println("x" + x);
		 t3d.lookAt( new Point3d(0, y,z), new Point3d(0,0,0), new Vector3d(0,1,0));
		 t3d.invert();*/
		float z = (float) 5
				* (float) Math.sin(Math.toRadians(m_cameraViews[m_cameraEye]));
		if (z < 0)
			z = -z;
		t3d.lookAt(new Point3d(0, 0, -z - 2), new Point3d(0, 0, -20),
				new Vector3d(0, 1, 0));

		//t3d.setTranslation(new Vector3d(0,0,8));
		steerTG.setTransform(t3d);
		m_cameraEye++;
		if (m_cameraEye == 180) {
			m_cameraTimer.stop();
			m_cameraEye = 0;
		}
	}

	private void timerActionHorizontalCameraMotion(
			java.awt.event.ActionEvent evt) {
		ViewingPlatform vp = univ.getViewingPlatform();
		TransformGroup steerTG = vp.getViewPlatformTransform();
		Transform3D t3d = new Transform3D();
		steerTG.getTransform(t3d);
		//Vector3d s = new Vector3d();
		Vector3f currPos = new Vector3f();
		t3d.get(currPos);

		// System.out.println("current Pos:" + currPos);

		float x = (float) (float) Math.sin(Math
				.toRadians(m_cameraViews[m_cameraEye]));
		float z = 2.41f - Math.abs(x);
		//((float)Math.sin(Math.toRadians(m_cameraViews[m_cameraEye])));
		// default (0, 0, 2.41)
		// System.out.println("x" + x);
		t3d.lookAt(new Point3d(x, 0, z), new Point3d(0, 0, 0), new Vector3d(0,
				1, 0));

		t3d.invert();

		//t3d.setTranslation(new Vector3d(0,0,8));
		steerTG.setTransform(t3d);
		m_cameraEye++;
		if (m_cameraEye == 360) {
			m_cameraTimer.stop();
			m_cameraEye = 0;
		}
	}

	// Resume Button Action
	private void startSimulation(java.awt.event.ActionEvent evt) {

		//    	if (!rightPanel.isVisible()){
		//    		rightPanel.setVisible(true);
		//    		bottomPanel.setVisible(true);
		//    	}
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/stop.png");
		startButton.setIcon(icon);
		startButton.setText("Stop");
		enableStage(0); // -1 	
		reStartButton.setEnabled(true);
		nextButton.setEnabled(true);
		//        outputGraph.setState(1);
		//        outputGraph2.setState(1);
		//        
		if (valChange) {
     
			int d=(int)fields[1];
			String str=getDataFile(BOS,d);
		//	System.out.println(str);
			freeBody.init(str,d);

			float scaleXZ = (float) (fields[1]);
			Vector3d scaleVec = new Vector3d(scaleXZ, 1, scaleXZ);
			Transform3D scaleT = new Transform3D();
			scaleT.setScale(scaleVec);
			objSwitchPos.setTransform(scaleT);

			LineAttributes la = new LineAttributes();
			la.setLineWidth((float) fields[0] / 10);
			appea.setLineAttributes(la);
		}

		timer.start();
		System.out.println("Timer started");
	}
     
	// Resume Button Action
//	private void timerActionPerformed(java.awt.event.ActionEvent evt) {
		//System.out.println("choo");
		
	/*	int i;
		if (cnt == 0)
			objSwitch.setWhichChild(0);
		stage++;
		Transform3D sieve = new Transform3D();
		Transform3D sand4 = new Transform3D();
		Transform3D sand3 = new Transform3D();
		Transform3D sand2 = new Transform3D();
		Transform3D sand1 = new Transform3D();
		Transform3D sand0 = new Transform3D();
		sieve.setTranslation(new Vector3d(x / 10, x / 50, 0));
		if (cnt == 2) {
			//System.out.println("cho");
			if (stage > 150) {
				x = 0;
				cnt = 3;
			}
			sievePos.setTransform(sieve);

			if (state == 0 && x > -.10) {
				//System.out.println("value of x is" + x);
				x = (x - (float) 0.010f);
				if (x < -.10)
					state = 1;
			}
			if (state == 1 && x < .10f) {
				//System.out.println("value of x is" + x);
				x = (x + (float) .01f);
				if (x >= .1f)
					state = 0;
			}

			if (stage < 130 && stage > 0) {
				// sand.setTranslation(new Vector3d(0,-(stage/1000),0));
				// sand.setTranslation(new Vector3d(0,0,0));
				sand4.setTranslation(new Vector3d(0,
						.372 + .055 - (float) (stage / 800), 0));
				sand4.setScale(new Vector3d(1,
						1.0 / (1 + (float) (stage / 30.0)), 1));
				cyl4.setTransform(sand4);

				// cyl3.setTransform(sand1);
			}

			if (stage < 85 && stage > 10) {
				sand3.setTranslation(new Vector3d(0,
						.265 + .055 + (float) (stage / 550), 0));
				sand3.setScale(new Vector3d(1, (float) (stage / 3.0), 1));
				cyl3.setTransform(sand3);
			}

			if (stage < 120 && stage > 30) {
				sand2.setTranslation(new Vector3d(0,
						.16 + .055 + (float) (stage / 550), 0));
				sand2.setScale(new Vector3d(1, (float) (stage / 3), 1));
				cyl2.setTransform(sand2);
			}

			if (stage < 120 && stage > 50) {
				sand1.setTranslation(new Vector3d(0,
						.06 + .055 + (float) (stage / 550), 0));
				sand1.setScale(new Vector3d(1, (float) (stage / 3), 1));
				cyl1.setTransform(sand1);
				
			}

			if (stage < 130 && stage > 70) {
				sand0.setTranslation(new Vector3d(0, -.06 + .055
						+ (float) (stage / 550), 0));
				sand0.setScale(new Vector3d(1, (float) (stage / 3), 1));
				cyl0.setTransform(sand0);
				int d=(int)fields[1];
				getDataFile(BOS,d);
							
			}*/
	//	}
	//	cellData = new String[10][5];
		          
		         
		/*table = new JTable(cellData, columnNames)
		{
			 @Override
		        public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return true;
		        }
		};
		jsp = new JScrollPane(table);

	//	rightPanel.add(jsp);

		if (rightPanel.isVisible()) {
			String [][]resultSet=new String[10][5];
			resultSet=freeBody.init(BOS,(int)fields[1]);
		//	System.out.println(BOS);
		//	System.out.println(fields[1]);
			//	cellData=resultSet;
			          for(int i1=0;i1<10;i1++)
			        	  for (int j1=0;j1<5;j1++)
			        	  {
			        		  cellData[i1][j1]=resultSet[i1][j1];
			        		  System.out.println(resultSet[i1][j1]);
			        	  }
		}

		java.util.BitSet visibleNodes = new java.util.BitSet(objSwitch
				.numChildren());
		visibleNodes.set(stage);
		objSwitch.setChildMask(visibleNodes);
		if (stage == 1)
			objSwitch.setWhichChild(0);
		if (stage == 2)
			objSwitch.setWhichChild(1);
		if (stage == 3)
			objSwitch.setWhichChild(2);
		if (stage == 0)
			objSwitch.setWhichChild(3);

		return;*/
	//}
	 private void timerActionPerformed(java.awt.event.ActionEvent evt)
	  {
	    
	  	    	
	  	    	
//		  float Water = (float) (freeBody.getWW());
//			float Soil = (float) (freeBody.getWS());

			int i = 0;
			// ///////// Text

//			outlbl_val[i++].setText(String.valueOf(Water) + " gm");
//			outlbl_val[i++].setText(String.valueOf(Soil) + " gm");
	     
	      if(rightPanel.isVisible())
	      {
//	      	outputGraph.drawGraph();
//	      	outputGraph2.drawGraph();
	     
	      }
	              
//	      fullViewGraph.updateGraph(new float[]{disp});
//	      fullViewGraph.drawGraph();
	      
//	      float  mx_disp = (float)outputGraph.getAbsMaximumY();

	      
	     // freeBody.update();        
	      
	      if(freeBody.isDataCompleted()) {
	      	pauseSimulation();
	      	return;
	      }
	      //java.util.BitSet mask = new java.util.BitSet(objSwitch.numChildren()); 
	      //System.out.println(objSwitch.numChildren());
	      //objSwitch.setChildMask(mask);        
	    stage++;
	   //  stage = stage%3;
	      
	             	
	      java.util.BitSet visibleNodes = new java.util.BitSet( objSwitch.numChildren() );
	      visibleNodes.set(stage);
	      objSwitch.setChildMask(visibleNodes);
//	      objSwitch.setWhichChild(-3);
	      
	      if(stage==1)
	      	objSwitch.setWhichChild(0);
	      if(stage==2)
	      	objSwitch.setWhichChild(1);
	 //     if(stage==3)
	   //   	objSwitch.setWhichChild(2);
	      if(stage==0)
	      	objSwitch.setWhichChild(3);

	    if(stage==4)
	        	objSwitch.setWhichChild(3);
	  //    if(stage==2)
	    //  	objSwitch.setWhichChild(2);
//	      if(stage==6)
	  //      	objSwitch.setWhichChild(0);
	     // if(stage==7)
	        //	objSwitch.setWhichChild(1);

	   /*
	      if(stage==0)
	      	objSwitch.setWhichChild(2);
	      if(stage==1)
	      	objSwitch.setWhichChild(0);
	      if(stage==2)
	      	objSwitch.setWhichChild(1);*/

	      return;            
	  }

	
	private void updateSimulationBody(double disp) {

		Shape3D shape = (Shape3D) hm.get("block1");
		shape.setGeometry(m_j3d.createBoxGeom((float) disp * 3));

		TransformGroup tgp = (TransformGroup) hm.get("roof1");
		Transform3D trans = new Transform3D();
		tgp.getTransform(trans);
		trans.setTranslation(new Vector3d(disp - 0, 0.17, -.1));
		tgp.setTransform(trans);

	}

	private void pauseSimulation() {

		timer.stop();
		ImageIcon icon = m_j3d.createImageIcon("resources/icons/start.png");
		startButton.setIcon(icon);
		startButton.setText("Start");
		reStartButton.setEnabled(true);
		// bottomPanel.setVisible(true);
		nextButton.setEnabled(true);

		rightPanel.setVisible(true);
		//enableStage(stage);
		//	outputGraph.setState(0);
		//		outputGraph2.setState(0);
		//startButton.setEnabled(true);

		valChange = false;

	//	repaint();
	}

}


class CompressionBody 
 {
	String tabledata[][];
    double data,data1,data2,data3,data4;
	public String[][] init(String datafile,int mass)
	{

	 String data_file = "resources/Sieve/Type1-1/data.txt";
     StringBuffer strBuff;
     URL url = Resources.getResource(data_file);
     try
     {    	tabledata=new String[10][5];
     		//cellData = new String[10][5];
	    	InputStream in = url.openStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			strBuff = new StringBuffer();
	    	
		//	System.out.println("Reading File" + datafile); 
	      	String str;
     	    int x=0;
			while ((str = bf.readLine()) != null)
	  	    {
			//	System.out.println(str);
	  	        String delimit="	";
	  	        String[] tokens = str.split(delimit);
	  	        if(tokens.length == 5)
	  	        {
	  	        	 
	  			     tabledata[x][0]=tokens[0].trim(); 
	  			     tabledata[x][1]=tokens[1].trim();	
	  			     tabledata[x][2]=tokens[2].trim(); 
	  			     tabledata[x][3]=tokens[3].trim();	
	  				 tabledata[x][4]=tokens[4].trim(); 
	  	
	  			     
	  	        }  	   
	  	        x++;
	  	    }
	  	}
	  	catch (Exception e2)
	  	{
	  		System.out.println("Some Error in opening file " + e2); 
	  	}
	 // 	System.out.println(tabledata);
	  	return tabledata;
	  	
	}
	public boolean isDataCompleted() {
		// TODO Auto-generated method stub
		return false;
	}
 }

