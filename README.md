# Offline Password Manager

## Description
This program seeks to predictably generate passwords given one general password and the name of what you're using the password for (alongside password requirements where these deviate from default settings).
Any centralized repository for password management is inherently potentially exploitable. While such industries take great care to follow cybersecurity best practices, password managers are an incredibly lucrative target for malicious actors, and a decentralized method, without your passwords being stored offsite, is an intriguing concept.
This app seeks to explore this option (purely as a proof of concept; this program is NOT intended to be cryptographically secure). 

## Functionality
In order to allow users to switch devices and get the same passwords while not having these passwords in the hands of a middle man, this program generates custom passwords from a predictable algorithm. 
For a more formal and cryptographically secure commercial product, this algorithm would need to be highly secretive to avoid reverse engineering and acquiring the core password, but as this is just a pet project (potentially for a portfolio), such secrecy is not necessary. 
