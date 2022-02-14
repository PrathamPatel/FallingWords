# Babbel Technical Test
## App name: Falling Words

This app is a simple language game. The player will see a word in one language - English - in this case. While that word is being displayed, a word in another language - Spanish - in this case and will be falling down from the top of the screen. The goal is to decide whether the falling words is the correct translation of the given word in English before the falling words reaches the bottom of the screen. The game will display 10 words, and the final score will be out of 10. If you do not answer before the word reaches the bottom of the screen, you will not be awarded the points.

## Total Time Invested: 
Total time invested was approximately 4.5 hours.

## Distribution of Time:
- Planning (drawing rough sketchs of UI, flow diagram, writing pseudocode of game logic) : 1 hour approximately
- Logic and UI (views for the different fragments + game logic - including models + fetching data from `.json` file) : 2.5 hours approximately
- Manual Testing : 30min approximately
- Writing Unit Tests : 30min approximately

## Decisions made for solving certain aspects of the game:
In order to keep development flow simple and ensure that majority of the business logic is extracted into the ViewModels, I made the follownig decisions:
1. I decided to make use of live data in the `GameViewModel`, so that I can make use of 2-way data-binding with the fragment, so as the game progresses, the view is rendered automatically.
2. I created Base Classes (`BaseFragment`, `BaseViewModel`, `BaseNavigation`) - these are generic classes that all fragments and ViewModels extends. The `BaseFragment` observes a liveData object of type `BaseNavigation` from the `BaseViewModel` class so that even the navigation logic can be extracted to the ViewModel, making the fragment code look much cleaner and easier to implement.
3. In order to achieve a smooth animation view, I made use of the android built in `ViewPropertyAnimator` which can be directly attached to the view.
4. I made use of `RxJava2` for threading and making sure we fetch the data from the `.json` file in the background while observing the main thread, so that once we receive the results, we can continue with out next set of operations. This prevents a thread race condition.

## Decisions made due to restricted time
In order to solve the problem within the given time frame, I made the following decisions:
1. To make use of data-binding as much as possible, as that saves time and is directly connected with the view and viewModel. 
2. Keeping UI simple, yet decent looking, by making use of built in Material components and a royalty free font downloaded during the planning phase.
3. I use navigation graphs and fragments only (reusability), as it reduces additional code required for navigating between the different views. Making use of the BaseClasses helps a lot as it reduces repetitive code.

## Improvements or Additions:
Should there have been more time, I would like to have made the following decisions:
1. Add different levels of difficulty with speed of the falling word increasing with each difficulty level.
2. Add and improve current unit tests and add intrumented (UI) tests.
3. Improve my code base and try make it more generic, for example, making custom views for the buttons and only changing properties.
