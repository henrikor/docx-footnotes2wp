# docx-footnotes2wp
Converts footnotes in docx document to Wordpress footnotes (kompatible with ie. Footnotes Made Easy plugin

## Example of usage:

mvn clean install exec:java -Dexec.mainClass="DocxFootnotesConverter" -Dexec.args="tmp/neomercantilism.docx tmp/neomercantilism_modified.docx"

(2 args: Input file + output file)

## This program is not tested yet.