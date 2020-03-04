#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#define _USE_MATH_DEFINES
#include <math.h>
#include <iostream>

float eyeX = 0;
float eyeY = 0;
float eyeZ = 4;
float angle = 0;
float vertical_angle = 0;
float base_side = 0.5;


void changeSize(int w, int h) {

    // Prevent a divide by zero, when window is too short
    // (you cant make a window with zero width).
    if(h == 0)
        h = 1;

    // compute window's aspect ratio
    float ratio = w * 1.0 / h;

    // Set the projection matrix as current
    glMatrixMode(GL_PROJECTION);
    // Load Identity Matrix
    glLoadIdentity();

    // Set the viewport to be the entire window
    glViewport(0, 0, w, h);

    // Set perspective
    gluPerspective(45.0f ,ratio, 1.0f ,1000.0f);

    // return to the model view matrix mode
    glMatrixMode(GL_MODELVIEW);
}

void keyPress(unsigned char key, int x, int y) {
    if(key == 'a') {
        angle += 0.01;
        eyeZ = 2* cos(angle);
        eyeX = 2* sin(angle);
    }
    if(key == 'd') {
        angle -= 0.01;
        eyeZ = 2* cos(angle);
        eyeX = 2* sin(angle);
    }
    if(key == 'w') {

    }
    if(key == 'z') {

    }
}

void rotate(void) {
    angle += 10;
    glutPostRedisplay();
}

void renderScene(void) {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // set the camera
    glLoadIdentity();
    gluLookAt(eyeX,eyeY , eyeZ,
              0.0,0.0,0.0,
              0.0f,1.0f,0.0f);

    glBegin(GL_LINES);// X axis in red
        glColor3f(1.0f, 0.0f, 0.0f);
        glVertex3f(-100.0f, 0.0f, 0.0f);
        glVertex3f( 100.0f, 0.0f, 0.0f);
        // Y Axis in Green
        glColor3f(0.0f, 1.0f, 0.0f);
        glVertex3f(0.0f, -100.0f, 0.0f);
        glVertex3f(0.0f, 100.0f, 0.0f);
        // Z Axis in Blue
        glColor3f(0.0f, 0.0f, 1.0f);
        glVertex3f(0.0f, 0.0f, -100.0f);
        glVertex3f(0.0f, 0.0f,  100.0f);
    glEnd();

    glRotatef(angle, 0, 1, 0);
    glBegin(GL_TRIANGLES);
        glColor3f(1.0f, 1.0f, 1.0f);
        int slices = 10;
        int radius = 2;
        float slice_step = 2 * M_PI * (1 / slices);
        float previous_x = 0;
        float previous_z = 0;
        float angle = slice_step;

        // base inferior
        for(int i = 1; i < slices; i++) {
            glVertex3f(0, 0, 0);
            glVertex3f(cos(angle) * radius, 0, sin(angle) * radius);
            glVertex3f(previous_x, 0, previous_z);
            angle += slice_step;
        }

        angle = slice_step;
        // base superior
        for(int i = 1; i < slices; i++) {
            glVertex3f(0, 2, 0);
            glVertex3f(cos(angle) * radius, 2, sin(angle) * radius);
            glVertex3f(previous_x, 2, previous_z);
            angle += slice_step;
        }

    glEnd();

    glutSwapBuffers();

}

int main(int argc, char **argv) {

    glutInit(&argc, argv);
    glutInitWindowPosition( 37, 42);
    glutInitWindowSize(800, 800);
    glutCreateWindow("Test");
    glutDisplayFunc(renderScene);
    glutReshapeFunc(changeSize);
    glutKeyboardFunc(keyPress);
    glutIdleFunc(rotate);

    // OpenGL settings
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);
    glClearColor(0.0f,0.0f,0.0f,0.0f);

    // enter GLUT's main loop
    glutMainLoop();

    return 1;

}
