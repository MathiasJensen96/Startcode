const Genderize = (props) => {
    return (
        <div>
          <h2>Genderize</h2>
          <form>
  <label>
    Name:
    <input id="nameToSearch" type="text" name="name" />
  </label>
  <input type="submit" value="Search" />
</form>
          <p>{props.name}</p>
          <p>{props.gender}</p>
          <p>{props.probability}</p>
        </div>
      );
}

export default Genderize;