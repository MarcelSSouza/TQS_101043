import React from 'react'
import { useState } from 'react'
import { Container } from 'react-bootstrap'
export default function MainPage() {
  return (
    <div>
      
      <Container>
   <h1>Pesquise por uma cidade:</h1>
    <input type="text" />
    <br></br>
    <br></br>
    <button>Buscar</button>
      </Container>
    </div>
  )
}
