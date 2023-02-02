https://pokeapi.co/

UI Inspired by
[Pokédex Application - UI Look & Feel](https://dribbble.com/shots/17031043-Pok-dex-Application-UI-Look-Feel/attachments/12114355?mode=media),
by Priscila K. on Dribbble, as well as [DREDEX: Pokedex App](https://dribbble.com/vbstahelin), by
Victor Binhara Stahelin on Dribbble.

TODOS

 - [X] Display all Pokemon on one page
 - [X] Integrate ~~hilt~~ Koin + Compose (+ ViewModel)
    - Using Koin rn as this is what we'll be using on T-Mobile, but can do Hilt eventually
 - [ ] Pagination?
   - Custom impl vs. paging3
 
Stretch Goals
 - [ ] Details screen
    - What to use for navigation??? `compose-navigation`? going to go to TMO
      Android sync and see what people think
 - [ ] CoordinatorLayout/Toolbar + NestedScrollView if possible

Eventually:
 - [ ] Testing / FragmentScenarios
    - How necessary are these with Compose? 
 - [ ] GraphQL
 - [ ] Add some juice