# Aurora Programming Language
![](https://img.shields.io/tokei/lines/github/noaether/aurora) ![](https://img.shields.io/badge/hours%20of%20sleep-none-green) \
Aurora is a programming language designed to be simple, efficient, and easy to learn. It is a statically typed language with a C-style syntax and is intended for use in system programming, scripting, and application development.

## Features
- Statically typed: Variables have a fixed data type that is determined at compile time.
- Simple syntax: The syntax is easy to read and write, making Aurora an excellent language for beginners.
- Expressive: Aurora supports a wide range of programming paradigms, including object-oriented, imperative, and functional programming.
- Fast: Aurora is designed to be efficient and optimized for speed.
- Cross-platform: Aurora programs can be compiled and run on a wide range of platforms, including Windows, macOS, and Linux.
- Easy to learn: Aurora is designed to be easy to learn, with a minimal set of keywords and simple syntax.
- Getting Started

To get started with Aurora, you will need to install the Aurora compiler on your system. You can download the latest version of the compiler from the Aurora website or from GitHub.

Once you have installed the compiler, you can start writing Aurora programs using your favorite text editor or integrated development environment (IDE).

## Examples
Here is a simple "Hello, World!" program in Aurora:

```
func main() {
    print("Hello, World!");
}
```
Here is a program that calculates the factorial of a number using a recursive function:

```
func factorial(n: int): int {
    if (n == 0) {
        return 1;
    } else {
        return n * factorial(n - 1);
    }
}

func main() {
    var n: int = 5;
    var result: int = factorial(n);
    print("The factorial of " + n + " is " + result);
}
```
## Documentation
The Aurora website contains detailed documentation on the Aurora programming language, including a language reference, tutorials, and examples. You can also find community resources, such as forums and chat rooms, where you can connect with other Aurora developers.

## Contributing
Aurora is an open-source project, and contributions are welcome! If you are interested in contributing to the Aurora project, please see the CONTRIBUTING.md file for more information.

##License
Aurora is licensed under the MIT License. See the LICENSE.md file for more information.
