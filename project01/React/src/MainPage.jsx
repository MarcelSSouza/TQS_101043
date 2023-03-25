import React from 'react'
import { useState } from 'react'
import { Container } from 'react-bootstrap'
import { Button } from 'react-bootstrap'
import axios from 'axios'
export default function MainPage() {
  const [city, setCity] = useState("")
  const [first, setfirst] = useState("")

  function search() {
    console.log(city)

    
    axios.get("http://localhost:8080/airquality/"+ city)
      .then(function (response) {
        console.log(response.data);
        setfirst(response.data)
      })
      .catch(function (error) {
        console.error(error);
      });
  }

  function search2() {
    console.log(city)
  }



  return (
    <div>
      
      <Container>
   <h1>Pesquise por uma cidade:</h1>
    <input type="text" style={{width: "100%" }} onChange={(e) => setCity(e.target.value) }  />
    <br></br>
    <br></br>
    <Button onClick={search}>Buscar</Button>

    <br></br>
    <h2>{first.city} - {first.countryCode}</h2>
    <h2>CO: {first.CO}</h2>
    <h2>AQI: {first.AQI}</h2>
    <h2>NO2: {first.NO2}</h2>
    <h2>OZONE: {first.OZONE}</h2>
    <h2>PM10: {first.PM10}</h2>
    <h2>PM25: {first.PM25}</h2>

    <Button onClick={search2}>Predictions for the next 5 days?</Button>


      </Container>
    </div>
  )
}
