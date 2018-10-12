# Pizza Delivery System

## Notes on Difficulty
- There seem to always be corner cases that pop up that make it difficult to deal with auto-generated code.
- We tried to create a convenience class to handle Address input and validation then give it a composition relationship to the order. However, the auto-generated code would give compile-time errors if we made the relationship 1 Order <@>- 1 Address (realistic). The solution is to make the relationship 1 Order <@>- 0..1 Address; however, that will remove the address from the constructor and make it not-essential. We added code in the controller to compensate, but it is non-ideal. Ultimately, because this wasn't a given requirement, we decided to eliminate the class altogether in favour of a simple string.