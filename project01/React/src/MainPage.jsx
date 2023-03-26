import React from 'react'
import { useState } from 'react'
import { Container } from 'react-bootstrap'
import { Button } from 'react-bootstrap'
import axios from 'axios'
export default function MainPage() {
  const [city, setCity] = useState("")
  const [first, setfirst] = useState("")
  const [second, setsecond] = useState("")

  function search() {
    axios.get("http://localhost:8080/airquality/" + city)
      .then(function (response) {
        console.log(response.data);
        setfirst(response.data.stations[0])
      })
      .catch(function (error) {
        console.error(error);
      });

    console.log(first)
  }

  function search2() {
    axios.get("http://localhost:8080/airquality/predictions/" + city)
      .then(function (response) {
        setsecond(response.data)
      })
      .catch(function (error) {
        console.error(error);
      });
  }



  return (
    <div>

      <Container>
        <h1>Pesquise por uma cidade:</h1>
        <input type="text" style={{ width: "100%" }} onChange={(e) => setCity(e.target.value)} />
        <br></br>
        <br></br>
        <Button onClick={search}>Buscar</Button>

        <br></br>
        <h2>{first.city} - {first.countryCode}</h2>
        <h4>CO: {first.CO}</h4>
        <h4>AQI: {first.AQI}</h4>
        <h4>NO2: {first.NO2}</h4>
        <h4>OZONE: {first.OZONE}</h4>
        <h4>PM10: {first.PM10}</h4>
        <h4>PM25: {first.PM25}</h4>

        <Button onClick={search2}>Predictions for the next 5 days?</Button>
        {second && second.map((item,key) => {
          console.log(item.components.co)
          return (
            <div>
              <h3>Day {key+1}</h3 >
              <h4>CO: {item.components.co}  NO2: {item.components.no2}  OZONE: {item.components.o3}</h4>
              <h4>PM10: {item.components.pm10}  PM25: {item.components.pm2_5}</h4>

            </div>
          )
        })}


      </Container>
    </div>
  )
}
