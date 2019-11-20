package br.com.joaopaulosj.vanhackathon2019.utils

import java.io.BufferedReader
import java.io.InputStreamReader


object Resources {
	
	fun read(fileName: String): String {
		val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
		
		if (inputStream != null) {
			val streamReader = BufferedReader(
					InputStreamReader(inputStream, "UTF-8"))
			val responseStrBuilder = StringBuilder()
			
			var inputStr = streamReader.readLine()
			while (inputStr != null) {
				responseStrBuilder.append(inputStr)
				inputStr = streamReader.readLine()
			}
			return responseStrBuilder.toString()
		}
		
		return "{}"
	}
	
}