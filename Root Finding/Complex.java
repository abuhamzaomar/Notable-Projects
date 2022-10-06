/*
 * This class implements a new type for complex numbers and corresponding
 * arithmetic operations
 */

public class Complex {
    /**
     * Real part x of the complex number x+iy.
     */
    private double x;
    
    /**
     * Imaginary part y of the complex number x+iy.
     */
    private double y;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Constructor: Initializes x, y.
     *
     * @param x  The initial value of the real component.
     * @param y  The initial value of the imaginary component.
     */
    public Complex(double x, double y) {
        
        this.x = x;
        this.y = y;
    }

    /**
     * Real constructor - initialises with a real number.
     *
     * @param x  The initial real number to initialise to.
     */
    public Complex(double x) {
        
        this(x, 0.0);
    }

    /**
     * Default constructor; initialise x and y to zero.
     */
    public Complex() {
        
        this(0.0, 0.0);
    }
    
    // ========================================================
    // Accessor and mutator methods.
    // ========================================================
    
    /**
     * Accessor Method: get real part of the complex number.
     * 
     * @return The real part of the complex number.
     */
    public double getReal() {
        
        return x;
    }

    /**
     * Accessor Method: get imaginary part of the complex number.
     *
     * @return The imaginary part of the complex number
     */
    public double getImag() {
        
        return y;
    }

    /**
     * Mutator method: set the real part of the complex number.
     *
     * @param x  The replacement real part of z.
     */
    public void setReal(double x) {
        
        this.x = x;
    }
    
    /**
     * Mutator method: set the imaginary part of the complex number.
     *
     * @param y  The replacement imaginary part of z.
     */
    public void setImag(double y) {
        
        this.y = y;
    }
    
    // ========================================================
    // Operations and functions with complex numbers.
    // ========================================================

    /**
     * Converts the complex number to a string.
     *
     * @return A string describing the complex number.
     */
    public String toString() {
        
        return String.format("%.3f%s%.3fi" , x , (y < 0.0 ? "-" : "+") , Math.abs(y));
    }

    /**
     * Computes square of the absolute value (magnitude) of the complex number
     * (i.e. |z|^2).
     *
     * @return The square of the absolute value of this complex number.
     */
    public double abs2() {
        
        return x*x + y*y;
    }

    /**
     * Computes absolute value (magnitude) of the complex number.
     *
     * @return The absolute value of the complex number.
     */
    public double abs() {
        
        return Math.sqrt(this.abs2());
    }
    
    /**
     * Calculates the conjugate of this complex number.
     *
     * @return A Complex containing the conjugate.
     */
    public Complex conjugate() {
        
        return new Complex(this.x, -this.y);
    }

    /**
     * Adds a complex number to this one.
     *
     * @param b  The complex number to add to this one.
     * @return   The sum of this complex number with b.
     */
    public Complex add(Complex b) {
        
        return new Complex(this.x + b.x, this.y + b.y);
    }
    
    /**
     * Calculates -z.
     *
     * @return The complex number -z = -x-iy
     */
    public Complex negate() {
        
        return new Complex(-this.x, -this.y);
    }

    /**
     * Multiplies this complex number by a constant.
     *
     * @param alpha   The constant to multiply by.
     * @return        The product of alpha with z.
     */
    public Complex multiply(double alpha) {
        
        return new Complex(alpha*this.x, alpha*this.y);
    }
    
    /**
     * Multiplies this complex number by another complex number.
     *
     * @param b   The complex number to multiply by.
     * @return    The product of b with z.
     */
    public Complex multiply(Complex b) {
        
        return new Complex(this.x * b.x - this.y * b.y, this.x * b.y + this.y * b.x);
    }

    /**
     * Divide this complex number by another.
     *
     * @param b  The complex number to divide by.
     * @return   The division z/a.
     */
    public Complex divide(Complex b) {
        
        Complex ret;
        double   alpha;
        alpha = b.abs2();
        ret   = this.multiply(b.conjugate());
        ret   = ret.multiply(1.0/alpha);
        return ret;
    }
        
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        Complex A = new Complex(2.0, 2.0);
        Complex B = new Complex(1.0);
        Complex C = new Complex();
        
        System.out.println("Constructor test:");
        System.out.println("A = "+A.toString());
        System.out.println("B = "+B.toString());
        System.out.println("C = "+C.toString());
        
        // Testing accessors/mutators.
        System.out.println();
        System.out.println("Setting imag(C) = real(C) + 1:");
        C.setImag(C.getReal() + 1.0);
        System.out.println("C = "+C.toString());
        
        // Operation testing
        System.out.println();
        System.out.println("Testing operators:");
        System.out.println("abs(A)   = "+A.abs());
        System.out.println("abs2(A)  = "+A.abs2());
        System.out.println("abs(B)   = "+B.abs());
        System.out.println("abs2(B)  = "+B.abs2());
        System.out.println("conj(A)  = "+A.conjugate());
        System.out.println("conj(B)  = "+B.conjugate());
        System.out.println("neg(A)   = "+A.negate());
        System.out.println("neg(B)   = "+B.negate());
        System.out.println("A+B      = "+A.add(B));
        System.out.println("A+C      = "+A.add(C));
        System.out.println("2*A      = "+A.multiply(2.0));
        System.out.println("A*C      = "+A.multiply(C));
        System.out.println("B*C      = "+B.multiply(C));
        System.out.println("A*A      = "+A.multiply(A));
        System.out.println("A/B      = "+A.divide(B));
        System.out.println("A/C      = "+A.divide(C));
        System.out.println("A/A      = "+A.divide(A));
        System.out.println("A-B      = "+A.add(B.negate()));

        // Testing chained operations
        System.out.println();
        System.out.println("Chained operators:");
        System.out.println("B*(A+C)    = "+B.multiply(A.add(C)));
        System.out.println("-A+B*C     = "+A.negate().add(B.multiply(C)));
    }
}