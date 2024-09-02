## Take Home Assignment - Android SE II

#### Jetpack Compose
Jetpack Compose is now the recommended way to make Android Apps

### Decisions and Assumptions
#### Architecture
- MVVM was chosen as it recommended by Google
- Basic routing was setup in anticipation of an additional screen in the future
- SearchRoute -> SearchScreen to move the ViewModel initialization and collectAsState() from NavHost
- DI is done with Dagger

#### UI Design
- Used mainly 1st party Material3 components

#### Postal Codes

- Postal Code can be entered manually
- Default is 90210
- Postal Code is CAD or USA only

#### Data 
- Assumed the only guaranteed non-null fields are ids

#### State Management
- SearchScreen only has state directly related to the functioning of the UI - e.g isActive
- All data and logic is moved to the ViewModel
- State is passed into the Composable via SearchResultUIState, and TextFieldStates
- State in the ViewModel is done using MutableStateFlow instead of mutableStateOf as (1) get access to collectAsStateWithLifecycle() and (2) can use flow operators (flow operators ideal for adding a filter feature for example)
- Fields have been abstracted into TextFieldState as error states also need to be managed (e.g errors for invalid postal code)
