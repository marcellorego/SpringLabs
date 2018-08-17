SHELL := /usr/bin/env bash
.DEFAULT_GOAL := help
.PHONY: rm run

ROOT_FOLDER:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))

help:
	@echo ''
	@echo 'Makefile for '
	@echo '     make help		shows this information'
	@echo '     make config		runs config-server'
	@echo '     make discovery		runs discovery-server'
	@echo '     make gateway		runs gateway-server'
	@echo '     make book		runs book-service'
	@echo '     make rating		runs rating-service'
	
config:
	cd $(ROOT_FOLDER)/config-server; mvn spring-boot:run

discovery:
	cd $(ROOT_FOLDER)/discovery-server;	mvn spring-boot:run

gateway:
	cd $(ROOT_FOLDER)/gateway-server;	mvn spring-boot:run

book:
	cd $(ROOT_FOLDER)/book-service;	mvn spring-boot:run

rating:
	cd $(ROOT_FOLDER)/rating-service;	mvn spring-boot:run