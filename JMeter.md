# Disclaimer
The following steps were executed on Windows, on other OS the steps may vary.

# Installation
JMeter requires Java 8 or higher.

JMeter can be downloaded from [here](https://jmeter.apache.org/download_jmeter.cgi) by using the the zip-file. The downloaded file can be verified by checking the hash with the following command:
```bash
certUtils -hashfile <downloaded-zip-file> SHA512
```
The output can then be compared with the SHA512 available on the download site.

## JMeter
The JMeter GUI which can be used to build a test plan can be started by executing the `jmeter.bat` file.

Through the menu `File` -> `Template` a test plan can be created from a template.
I used the `Building a web test plan` template.

To create a POST request it's important to add
- `Content-Type: application/json` to the HTTP Header Manager
- Set the content encoding of the request to `utf-8`

### CLI Usage
To run jmeter from the CLI cd into the directory where JMeter is installed first.

Run any jmeter script in non-GUI mode with the following command to generate a report dashboard:
```bash
jmeter -n -t <script-path>.jmx -l <output-path>
```
```bash
jmeter -g <file-path> -o <folder-path>
```
| Argument | Description |
| -------- | ----------- |
| -n | run in non-GUI mode |
| -t | path to JMX file that contains the test plan |
| -l | path of file to log sample results to |
| -g | path to csv file for generating report dashboard |
| -o | output folder where report dashboard should be generated |