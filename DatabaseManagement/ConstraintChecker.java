package DatabaseManagement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ConstraintChecker implements ConstraintChecks {

    private String metaDataFile = "Metadata.json";
    public JSONArray metaData;
    private HashMap<Constraint, ValidationFunction> constraint_to_validator;

    public ConstraintChecker() {
        constraint_to_validator = new HashMap<>();
        readMetaDataFromFile();

    }

    private void readMetaDataFromFile() {
        try {
            FileInputStream fin = new FileInputStream(metaDataFile);
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bin = new BufferedReader(in);

            String fileContent = "";
            String line;
            while ((line = bin.readLine()) != null)
                fileContent += line;

            metaData = (JSONArray) new JSONParser().parse(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void check(Table t, HashMap<String, String> attributesToCheck)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        JSONObject table = getTableInfoFromMetaData(t.getTableName());
        JSONObject tableAttributes = (JSONObject) table.get("Attributes");

        for (String attribute : attributesToCheck.keySet()) {
            if (!tableAttributes.containsKey(attribute))
                throw new AttributeNotFoundException(t.getTableName(), attribute);

            JSONArray attributeConstraints = (JSONArray) tableAttributes.get(attribute);
            for (Object obj : attributeConstraints) {
                String constraint = (String) obj;

                if (constraint.startsWith("R"))
                    validateReferentialIntegrity();

                else if (!constraint_to_validator.containsKey(constraint))
                    throw new ConstraintNotFoundException(constraint);

                ValidationFunction validator = constraint_to_validator.get(constraint);
                validator.validate(constraint);
            }
        }

    }

    private JSONObject getTableInfoFromMetaData(String tableName) throws TableNotFoundException {
        for (Object table : metaData) {
            JSONObject tableObject = (JSONObject) table;
            if (tableObject.get("TableName").equals(tableName))
                return tableObject;
        }
        throw new TableNotFoundException();
    }
    // ///////////////////////////////////////////VALIDATORS/////////////////////////////////////////////////

    private void validateReferentialIntegrity() {
    }

    public static void main(String[] args) {
        ConstraintChecker cK = new ConstraintChecker();
        try {
            FileOutputStream fout = new FileOutputStream("Metadata2.json");
            OutputStreamWriter out = new OutputStreamWriter(fout);
            out.write(cK.metaData.toJSONString());
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
