# Scala Future onComplete: Premature Program Exit

This example demonstrates a common error when using Scala's `Future` with `onComplete`.  Because `onComplete` doesn't block, the program continues execution and exits before the asynchronous operation within the `Future` completes.  This leads to the results or exceptions being ignored.

The `bug.scala` file shows the erroneous code.  The `bugSolution.scala` file provides a corrected version using `Await.result` for synchronous behavior (suitable for simple cases) or a more robust approach with callbacks or managing the program lifecycle to wait for the Future.