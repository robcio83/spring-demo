package com.sample.order

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Order(@Id @GeneratedValue var id: Long?, val date: Long)
