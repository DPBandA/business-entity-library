# Things to do

- Add head and active fields to Business class and table.
- Order PhoneNumber, Internet as is done with address and contact.
- Impl finding classifications by category and use it when finding job classifications
- Add "identification" and "identificationType" to Client class.

### Legal Doc report search code:

} else if (activeTabIndex == 1) { // report based search
    if (documentReport == null) { // get first listed report
        List<DocumentReport> reports = DocumentReport.findAllDocumentReports(em);
        if (reports != null) {
            if (!reports.isEmpty()) {
                documentReport = reports.get(0);
            }
        }
    } else if (documentReport.getId() != null) {
        documentReport = DocumentReport.findDocumentReportById(em, documentReport.getId());
    } else { // get first report
        List<DocumentReport> reports = DocumentReport.findAllDocumentReports(em);
        if (reports != null) {
            if (!reports.isEmpty()) {
                documentReport = reports.get(0);
            }
        }
    }

    // do actual report generation here
    if (documentReport.getShowNumberOfDocuments()) {// this means a report that involves grouping
        documentSearchResultList = LegalDocument.findGroupedLegalDocumentsByDateSearchField(em,
                dateSearchField, searchType,
                getDatePeriod().getStartDate(), getDatePeriod().getEndDate());
    } else if (!documentReport.getShowNumberOfDocuments()) { // report that includes all documents within the specified dates
        documentSearchResultList = LegalDocument.findLegalDocumentsByDateSearchField(em,
                dateSearchField, searchType, "",
                getDatePeriod().getStartDate(), getDatePeriod().getEndDate());
    }
}