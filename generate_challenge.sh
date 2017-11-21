#!/bin/bash
curl --data-raw '{"instructions": "Speak friend and enter", "solution": "friend"}' -H 'Content-Type: application/json' http://localhost:8080/challenge
