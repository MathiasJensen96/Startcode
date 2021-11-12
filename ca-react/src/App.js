import logo from './logo.svg';
import './App.css';
import "./style2.css";
import Bored from "./components/Bored";
import Cat from './components/Cat';
import Dog from './components/Dog';
import Genderize from './components/Genderize';
import React, { useEffect, useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  NavLink
} from "react-router-dom";


// This site has 3 pages, all of which are rendered
// dynamically in the browser (not server rendered).
//
// Although the page does not ever refresh, notice how
// React Router keeps the URL up to date as you navigate
// through the site. This preserves the browser history,
// making sure things like the back button and bookmarks
// work properly.

export default function BasicExample() {
  const [activity, setActivity] = useState("");
  const [type, setType] = useState("");
  const [fact, setFact] = useState("");
  const [message, setMessage] = useState("");
  const [name, setName] = useState("");
  const [gender, setGender] = useState("");
  const [probability, setProbability] = useState("");
  

  useEffect( () => {
      fetch("https://www.boredapi.com/api/activity")
      .then(res => res.json())
      .then(data => (setActivity(data.activity), setType(data.type)))
  },[])

  useEffect( () => {
    fetch("https://catfact.ninja/fact")
    .then(res => res.json())
    .then(data => setFact(data.fact))
},[])

useEffect( () => {
  fetch("https://dog.ceo/api/breeds/image/random")
  .then(res => res.json())
  .then(data => setMessage(data.message))
},[])

useEffect( () => {
  fetch(`https://api.genderize.io/?name=${name}`)
  .then(res => res.json())
  .then(data => (setName(data.name), setGender(data.gender), setProbability(data.probability)))
},[])


  return (
    <Router>
      <div>
        <ul className="header">
          <li>
            <NavLink exact activeClassName="selected" to="/">Bored</NavLink>
          </li>
          <li>
            <NavLink exaxt activeClassName="selected" to="/cat">Cat</NavLink>
          </li>
          <li>
            <NavLink exact activeClassName="selected" to="/dog">Dog</NavLink>
          </li>
          <li>
            <NavLink exact activeClassName="selected" to="/genderize">Genderize</NavLink>
          </li>
        </ul>

        <hr />

        {/*
          A <Switch> looks through all its children <Route>
          elements and renders the first one whose path
          matches the current URL. Use a <Switch> any time
          you have multiple routes, but you want only one
          of them to render at a time
        */}
        <div className="content">
        <Switch>
          <Route exact path="/">
            <Bored activity={activity} type={type} />
          </Route>
          <Route path="/cat">
            <Cat fact={fact}/>
          </Route>
          <Route path="/dog">
            <Dog message={message} />
          </Route>
          <Route path="/genderize">
            <Genderize name={name} gender={gender} probability={probability} />
          </Route>
        </Switch>
        </div>
      </div>
    </Router>
  );
}