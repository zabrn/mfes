
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Problem {
  public String description;
  public Boolean solved;
  public Printer printer;
  public Employee employee;

  public void cg_init_Problem_1(
      final String descriptionC, final Printer printerC, final Employee employeeC) {

    description = descriptionC;
    printer = printerC;
    printer.functional = false;
    employee = employeeC;
    employee.addProblem(this);
    solved = false;
  }

  public Problem(final String descriptionC, final Printer printerC, final Employee employeeC) {

    cg_init_Problem_1(descriptionC, printerC, employeeC);
  }

  public void solveIt() {

    solved = true;
  }

  public Problem() {}

  public static Boolean twoProblemsUnsolved(final Problem p1, final Problem p2) {

    Boolean andResult_22 = false;

    if (Utils.equals(p1.printer, p2.printer)) {
      Boolean andResult_23 = false;

      if (Utils.equals(p1.solved, true)) {
        Boolean andResult_24 = false;

        if (Utils.equals(p2.solved, true)) {
          if (Utils.equals(p1.employee, p2.employee)) {
            andResult_24 = true;
          }
        }

        if (andResult_24) {
          andResult_23 = true;
        }
      }

      if (andResult_23) {
        andResult_22 = true;
      }
    }

    return andResult_22;
  }

  public static Boolean cg_equals(final Problem p1, final Problem p2) {

    Boolean andResult_25 = false;

    if (Utils.equals(p1.printer, p2.printer)) {
      Boolean andResult_26 = false;

      if (Utils.equals(p1.solved, p2.solved)) {
        Boolean andResult_27 = false;

        if (Utils.equals(p1.description, p2.description)) {
          if (Utils.equals(p1.employee, p2.employee)) {
            andResult_27 = true;
          }
        }

        if (andResult_27) {
          andResult_26 = true;
        }
      }

      if (andResult_26) {
        andResult_25 = true;
      }
    }

    return andResult_25;
  }

  public String toString() {

    return "Problem{"
        + "description := "
        + Utils.toString(description)
        + ", solved := "
        + Utils.toString(solved)
        + ", printer := "
        + Utils.toString(printer)
        + ", employee := "
        + Utils.toString(employee)
        + "}";
  }
}
