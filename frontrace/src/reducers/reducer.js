const types = {
  addedPlayers: "addedPlayers",
  addGameId: "addGameId"
}

const initialStore = {
  players: [{id:"", playerName:"" }],
  tracklength: "",
  carGameId: ""
}



const storeReducer = (state,action) => {
  const { v4: uuidv4 } = require("uuid");

  switch(action.type) {
    case types.addedPlayers:
      return {
        ...state,
        players: state.playersName
      }
      case types.addGameId:
        return {
          carGameId: uuidv4()
        }
    default:
      return state;
  }

} 

export {initialStore,types}
export default storeReducer
