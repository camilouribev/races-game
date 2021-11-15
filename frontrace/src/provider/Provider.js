import React, { useReducer, createContext } from "react";
import reducer from "../reducers/reducer";
import { initialStore } from "../reducers/reducer";



const Store = createContext();

function StoreProvider(props) {
  const [state, dispatch] = useReducer(reducer, initialStore);

  return (
    <Store.Provider value={[state, dispatch] }>
      {props.children}
    </Store.Provider>
  );
}

export { Store, StoreProvider };