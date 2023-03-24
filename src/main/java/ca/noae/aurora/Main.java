package ca.noae.aurora;

import ca.noae.aurora.ast.*;
import ca.noae.aurora.parser.*;
import ca.noae.aurora.lexer.*;
import ca.noae.aurora.codegen.*;

public class Main {
    public static void main(String[] args) throws Lexer.LexicalException {
        // lex the source code
        String source = """
                class Person {
                  var name: String
                  var age: Int
                  init(name: String, age: Int) {
                    self.name = name
                    self.age = age
                  }
                    print("Hello, my name is (name) and I am (age) years old.")
                  }
                }
                let person = Person(name: "John", age: 30)
                person.sayHello()
                func repeatTask(times: Int, task: () -> Void) {
                  for i in 1...times {
                    task()
                  }
                }
                repeatTask(3) {
                  print("Task executed")
                }
                protocol Vehicle {
                  var numberOfWheels: Int { get }
                  func drive()
                }
                class Car: Vehicle {
                  var numberOfWheels: Int = 4
                  func drive() {
                    print("Driving the car")
                  }
                }
                let car = Car()
                // Call the drive method on the car instance
                car.drive()
                """;
        Lexer lexer = new Lexer(source);
        Token[] tokens = lexer.tokenize().toArray(new Token[0]);
        for (Token token : tokens) {
            System.out.println(token);
        }

        // parse the tokens
        // generate the AST

    }
}
