package com.google.ssmm.finance.hexun;

public class FinancialStatement {
        private String accountingYear;
        private double cashAndCashEquivalents;
        private double tradingFinancialAssets;
        private double billsReceivable;
        private double accountsReceivable;
        private double prepaidExpenses;
        private double otherReceivables;
        private double relatedPartyReceivables;
        private double interestReceivable;
        private double dividendReceivable;
        private double inventory;
        private double consumableBiologicalAssets;
        private double currentPortionOfNonCurrentAssets;
        private double otherCurrentAssets;
        private double totalCurrentAssets;
        private double availableForSaleFinancialAssets;
        private double heldToMaturityInvestments;
        private double longTermReceivables;
        private double longTermEquityInvestments;
        private double investmentProperties;
        private double fixedAssets;
        private double constructionInProgress;
        private double engineeringMaterials;
        private double fixedAssetsForDisposal;
        private double productiveBiologicalAssets;
        private double oilAndGasAssets;
        private double intangibleAssets;
        private double developmentExpenditure;
        private double goodwill;
        private double longTermPrepaidExpenses;
        private double deferredIncomeTaxAssets;
        private double otherNonCurrentAssets;
        private double totalNonCurrentAssets;
        private double totalAssets;
        private double shortTermBorrowings;
        private double tradingFinancialLiabilities;
        private double billsPayable;
        private double accountsPayable;
        private double advanceReceipts;
        private double employeeBenefitsPayable;
        private double taxesPayable;
        private double interestPayable;
        private double dividendPayable;
        private double otherPayables;
        private double relatedPartyPayables;
        private double currentPortionOfNonCurrentLiabilities;
        private double otherCurrentLiabilities;
        private double totalCurrentLiabilities;
        private double longTermBorrowings;
        private double bondsPayable;
        private double longTermPayables;
        private double specialPayables;
        private double estimatedLiabilities;
        private double deferredIncomeTaxLiabilities;
        private double otherNonCurrentLiabilities;
        private double totalNonCurrentLiabilities;
        private double totalLiabilities;
        private double paidInCapital;
        private double capitalReserves;
        private double surplusReserves;
        private double treasuryStock;
        private double undistributedProfits;
        private double minorityShareholdersEquity;
        private double foreignCurrencyReportTranslationDifference;
        private double abnormalBusinessIncomeAdjustment;
        private double ownersEquity;
        private double totalLiabilitiesAndOwnersEquity;

        // Add constructors, getters, and setters here

        // Override toString() method to display object values
        @Override
        public String toString() {
            return "FinancialStatement{" +
                    "accountingYear='" + accountingYear + '\'' +
                    ", cashAndCashEquivalents=" + cashAndCashEquivalents +
                    ", tradingFinancialAssets=" + tradingFinancialAssets +
                    ", billsReceivable=" + billsReceivable +
                    ", accountsReceivable=" + accountsReceivable +
                    ", prepaidExpenses=" + prepaidExpenses +
                    ", otherReceivables=" + otherReceivables +
                    ", relatedPartyReceivables=" + relatedPartyReceivables +
                    ", interestReceivable=" + interestReceivable +
                    ", dividendReceivable=" + dividendReceivable +
                    ", inventory=" + inventory +
                    ", consumableBiologicalAssets=" + consumableBiologicalAssets +
                    ", currentPortionOfNonCurrentAssets=" + currentPortionOfNonCurrentAssets +
                    ", otherCurrentAssets=" + otherCurrentAssets +
                    ", totalCurrentAssets=" + totalCurrentAssets +
                    ", availableForSaleFinancialAssets=" + availableForSaleFinancialAssets +
                    ", heldToMaturityInvestments=" + heldToMaturityInvestments +
                    ", longTermReceivables=" + longTermReceivables +
                    ", longTermEquityInvestments=" + longTermEquityInvestments +
                    ", investmentProperties=" + investmentProperties +
                    ", fixedAssets=" + fixedAssets +
                    ", constructionInProgress=" + constructionInProgress +
                    ", engineeringMaterials=" + engineeringMaterials +
                    ", fixedAssetsForDisposal=" + fixedAssetsForDisposal +
                    ", productiveBiologicalAssets=" + productiveBiologicalAssets +
                    ", oilAndGasAssets=" + oilAndGasAssets +
                    ", intangibleAssets=" + intangibleAssets +
                    ", developmentExpenditure=" + developmentExpenditure +
                    ", goodwill=" + goodwill +
                    ", longTermPrepaidExpenses=" + longTermPrepaidExpenses +
                    ", deferredIncomeTaxAssets=" + deferredIncomeTaxAssets +
                    ", otherNonCurrentAssets=" + otherNonCurrentAssets +
                    ", totalNonCurrentAssets=" + totalNonCurrentAssets +
                    ", totalAssets=" + totalAssets +
                    ", shortTermBorrowings=" + shortTermBorrowings +
                    ", tradingFinancialLiabilities=" + tradingFinancialLiabilities +
                    ", billsPayable=" + billsPayable +
                    ", accountsPayable=" + accountsPayable +
                    ", advanceReceipts=" + advanceReceipts +
                    ", employeeBenefitsPayable=" + employeeBenefitsPayable +
                    ", taxesPayable=" + taxesPayable +
                    ", interestPayable=" + interestPayable +
                    ", dividendPayable=" + dividendPayable +
                    ", otherPayables=" + otherPayables +
                    ", relatedPartyPayables=" + relatedPartyPayables +
                    ", currentPortionOfNonCurrentLiabilities=" + currentPortionOfNonCurrentLiabilities +
                    ", otherCurrentLiabilities=" + otherCurrentLiabilities +
                    ", totalCurrentLiabilities=" + totalCurrentLiabilities +
                    ", longTermBorrowings=" + longTermBorrowings +
                    ", bondsPayable=" + bondsPayable +
                    ", longTermPayables=" + longTermPayables +
                    ", specialPayables=" + specialPayables +
                    ", estimatedLiabilities=" + estimatedLiabilities +
                    ", deferredIncomeTaxLiabilities=" + deferredIncomeTaxLiabilities +
                    ", otherNonCurrentLiabilities=" + otherNonCurrentLiabilities +
                    ", totalNonCurrentLiabilities=" + totalNonCurrentLiabilities +
                    ", totalLiabilities=" + totalLiabilities +
                    ", paidInCapital=" + paidInCapital +
                    ", capitalReserves=" + capitalReserves +
                    ", surplusReserves=" + surplusReserves +
                    ", treasuryStock=" + treasuryStock +
                    ", undistributedProfits=" + undistributedProfits +
                    ", minorityShareholdersEquity=" + minorityShareholdersEquity +
                    ", foreignCurrencyReportTranslationDifference=" + foreignCurrencyReportTranslationDifference +
                    ", abnormalBusinessIncomeAdjustment=" + abnormalBusinessIncomeAdjustment +
                    ", ownersEquity=" + ownersEquity +
                    ", totalLiabilitiesAndOwnersEquity=" + totalLiabilitiesAndOwnersEquity +
                    '}';
        }


    public String getAccountingYear() {
        return accountingYear;
    }

    public void setAccountingYear(String accountingYear) {
        this.accountingYear = accountingYear;
    }

    public double getCashAndCashEquivalents() {
        return cashAndCashEquivalents;
    }

    public void setCashAndCashEquivalents(double cashAndCashEquivalents) {
        this.cashAndCashEquivalents = cashAndCashEquivalents;
    }

    public double getTradingFinancialAssets() {
        return tradingFinancialAssets;
    }

    public void setTradingFinancialAssets(double tradingFinancialAssets) {
        this.tradingFinancialAssets = tradingFinancialAssets;
    }

    public double getBillsReceivable() {
        return billsReceivable;
    }

    public void setBillsReceivable(double billsReceivable) {
        this.billsReceivable = billsReceivable;
    }

    public double getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(double accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }

    public double getPrepaidExpenses() {
        return prepaidExpenses;
    }

    public void setPrepaidExpenses(double prepaidExpenses) {
        this.prepaidExpenses = prepaidExpenses;
    }

    public double getOtherReceivables() {
        return otherReceivables;
    }

    public void setOtherReceivables(double otherReceivables) {
        this.otherReceivables = otherReceivables;
    }

    public double getRelatedPartyReceivables() {
        return relatedPartyReceivables;
    }

    public void setRelatedPartyReceivables(double relatedPartyReceivables) {
        this.relatedPartyReceivables = relatedPartyReceivables;
    }

    public double getInterestReceivable() {
        return interestReceivable;
    }

    public void setInterestReceivable(double interestReceivable) {
        this.interestReceivable = interestReceivable;
    }

    public double getDividendReceivable() {
        return dividendReceivable;
    }

    public void setDividendReceivable(double dividendReceivable) {
        this.dividendReceivable = dividendReceivable;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public double getConsumableBiologicalAssets() {
        return consumableBiologicalAssets;
    }

    public void setConsumableBiologicalAssets(double consumableBiologicalAssets) {
        this.consumableBiologicalAssets = consumableBiologicalAssets;
    }

    public double getCurrentPortionOfNonCurrentAssets() {
        return currentPortionOfNonCurrentAssets;
    }

    public void setCurrentPortionOfNonCurrentAssets(double currentPortionOfNonCurrentAssets) {
        this.currentPortionOfNonCurrentAssets = currentPortionOfNonCurrentAssets;
    }

    public double getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public void setOtherCurrentAssets(double otherCurrentAssets) {
        this.otherCurrentAssets = otherCurrentAssets;
    }

    public double getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    public void setTotalCurrentAssets(double totalCurrentAssets) {
        this.totalCurrentAssets = totalCurrentAssets;
    }

    public double getAvailableForSaleFinancialAssets() {
        return availableForSaleFinancialAssets;
    }

    public void setAvailableForSaleFinancialAssets(double availableForSaleFinancialAssets) {
        this.availableForSaleFinancialAssets = availableForSaleFinancialAssets;
    }

    public double getHeldToMaturityInvestments() {
        return heldToMaturityInvestments;
    }

    public void setHeldToMaturityInvestments(double heldToMaturityInvestments) {
        this.heldToMaturityInvestments = heldToMaturityInvestments;
    }

    public double getLongTermReceivables() {
        return longTermReceivables;
    }

    public void setLongTermReceivables(double longTermReceivables) {
        this.longTermReceivables = longTermReceivables;
    }

    public double getLongTermEquityInvestments() {
        return longTermEquityInvestments;
    }

    public void setLongTermEquityInvestments(double longTermEquityInvestments) {
        this.longTermEquityInvestments = longTermEquityInvestments;
    }

    public double getInvestmentProperties() {
        return investmentProperties;
    }

    public void setInvestmentProperties(double investmentProperties) {
        this.investmentProperties = investmentProperties;
    }

    public double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public double getConstructionInProgress() {
        return constructionInProgress;
    }

    public void setConstructionInProgress(double constructionInProgress) {
        this.constructionInProgress = constructionInProgress;
    }

    public double getEngineeringMaterials() {
        return engineeringMaterials;
    }

    public void setEngineeringMaterials(double engineeringMaterials) {
        this.engineeringMaterials = engineeringMaterials;
    }

    public double getFixedAssetsForDisposal() {
        return fixedAssetsForDisposal;
    }

    public void setFixedAssetsForDisposal(double fixedAssetsForDisposal) {
        this.fixedAssetsForDisposal = fixedAssetsForDisposal;
    }

    public double getProductiveBiologicalAssets() {
        return productiveBiologicalAssets;
    }

    public void setProductiveBiologicalAssets(double productiveBiologicalAssets) {
        this.productiveBiologicalAssets = productiveBiologicalAssets;
    }

    public double getOilAndGasAssets() {
        return oilAndGasAssets;
    }

    public void setOilAndGasAssets(double oilAndGasAssets) {
        this.oilAndGasAssets = oilAndGasAssets;
    }

    public double getIntangibleAssets() {
        return intangibleAssets;
    }

    public void setIntangibleAssets(double intangibleAssets) {
        this.intangibleAssets = intangibleAssets;
    }

    public double getDevelopmentExpenditure() {
        return developmentExpenditure;
    }

    public void setDevelopmentExpenditure(double developmentExpenditure) {
        this.developmentExpenditure = developmentExpenditure;
    }

    public double getGoodwill() {
        return goodwill;
    }

    public void setGoodwill(double goodwill) {
        this.goodwill = goodwill;
    }

    public double getLongTermPrepaidExpenses() {
        return longTermPrepaidExpenses;
    }

    public void setLongTermPrepaidExpenses(double longTermPrepaidExpenses) {
        this.longTermPrepaidExpenses = longTermPrepaidExpenses;
    }

    public double getDeferredIncomeTaxAssets() {
        return deferredIncomeTaxAssets;
    }

    public void setDeferredIncomeTaxAssets(double deferredIncomeTaxAssets) {
        this.deferredIncomeTaxAssets = deferredIncomeTaxAssets;
    }

    public double getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }

    public void setOtherNonCurrentAssets(double otherNonCurrentAssets) {
        this.otherNonCurrentAssets = otherNonCurrentAssets;
    }

    public double getTotalNonCurrentAssets() {
        return totalNonCurrentAssets;
    }

    public void setTotalNonCurrentAssets(double totalNonCurrentAssets) {
        this.totalNonCurrentAssets = totalNonCurrentAssets;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getShortTermBorrowings() {
        return shortTermBorrowings;
    }

    public void setShortTermBorrowings(double shortTermBorrowings) {
        this.shortTermBorrowings = shortTermBorrowings;
    }

    public double getTradingFinancialLiabilities() {
        return tradingFinancialLiabilities;
    }

    public void setTradingFinancialLiabilities(double tradingFinancialLiabilities) {
        this.tradingFinancialLiabilities = tradingFinancialLiabilities;
    }

    public double getBillsPayable() {
        return billsPayable;
    }

    public void setBillsPayable(double billsPayable) {
        this.billsPayable = billsPayable;
    }

    public double getAccountsPayable() {
        return accountsPayable;
    }

    public void setAccountsPayable(double accountsPayable) {
        this.accountsPayable = accountsPayable;
    }

    public double getAdvanceReceipts() {
        return advanceReceipts;
    }

    public void setAdvanceReceipts(double advanceReceipts) {
        this.advanceReceipts = advanceReceipts;
    }

    public double getEmployeeBenefitsPayable() {
        return employeeBenefitsPayable;
    }

    public void setEmployeeBenefitsPayable(double employeeBenefitsPayable) {
        this.employeeBenefitsPayable = employeeBenefitsPayable;
    }

    public double getTaxesPayable() {
        return taxesPayable;
    }

    public void setTaxesPayable(double taxesPayable) {
        this.taxesPayable = taxesPayable;
    }

    public double getInterestPayable() {
        return interestPayable;
    }

    public void setInterestPayable(double interestPayable) {
        this.interestPayable = interestPayable;
    }

    public double getDividendPayable() {
        return dividendPayable;
    }

    public void setDividendPayable(double dividendPayable) {
        this.dividendPayable = dividendPayable;
    }

    public double getOtherPayables() {
        return otherPayables;
    }

    public void setOtherPayables(double otherPayables) {
        this.otherPayables = otherPayables;
    }

    public double getRelatedPartyPayables() {
        return relatedPartyPayables;
    }

    public void setRelatedPartyPayables(double relatedPartyPayables) {
        this.relatedPartyPayables = relatedPartyPayables;
    }

    public double getCurrentPortionOfNonCurrentLiabilities() {
        return currentPortionOfNonCurrentLiabilities;
    }

    public void setCurrentPortionOfNonCurrentLiabilities(double currentPortionOfNonCurrentLiabilities) {
        this.currentPortionOfNonCurrentLiabilities = currentPortionOfNonCurrentLiabilities;
    }

    public double getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    public void setOtherCurrentLiabilities(double otherCurrentLiabilities) {
        this.otherCurrentLiabilities = otherCurrentLiabilities;
    }

    public double getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    public void setTotalCurrentLiabilities(double totalCurrentLiabilities) {
        this.totalCurrentLiabilities = totalCurrentLiabilities;
    }

    public double getLongTermBorrowings() {
        return longTermBorrowings;
    }

    public void setLongTermBorrowings(double longTermBorrowings) {
        this.longTermBorrowings = longTermBorrowings;
    }

    public double getBondsPayable() {
        return bondsPayable;
    }

    public void setBondsPayable(double bondsPayable) {
        this.bondsPayable = bondsPayable;
    }

    public double getLongTermPayables() {
        return longTermPayables;
    }

    public void setLongTermPayables(double longTermPayables) {
        this.longTermPayables = longTermPayables;
    }

    public double getSpecialPayables() {
        return specialPayables;
    }

    public void setSpecialPayables(double specialPayables) {
        this.specialPayables = specialPayables;
    }

    public double getEstimatedLiabilities() {
        return estimatedLiabilities;
    }

    public void setEstimatedLiabilities(double estimatedLiabilities) {
        this.estimatedLiabilities = estimatedLiabilities;
    }

    public double getDeferredIncomeTaxLiabilities() {
        return deferredIncomeTaxLiabilities;
    }

    public void setDeferredIncomeTaxLiabilities(double deferredIncomeTaxLiabilities) {
        this.deferredIncomeTaxLiabilities = deferredIncomeTaxLiabilities;
    }

    public double getOtherNonCurrentLiabilities() {
        return otherNonCurrentLiabilities;
    }

    public void setOtherNonCurrentLiabilities(double otherNonCurrentLiabilities) {
        this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
    }

    public double getTotalNonCurrentLiabilities() {
        return totalNonCurrentLiabilities;
    }

    public void setTotalNonCurrentLiabilities(double totalNonCurrentLiabilities) {
        this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
    }

    public double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public double getPaidInCapital() {
        return paidInCapital;
    }

    public void setPaidInCapital(double paidInCapital) {
        this.paidInCapital = paidInCapital;
    }

    public double getCapitalReserves() {
        return capitalReserves;
    }

    public void setCapitalReserves(double capitalReserves) {
        this.capitalReserves = capitalReserves;
    }

    public double getSurplusReserves() {
        return surplusReserves;
    }

    public void setSurplusReserves(double surplusReserves) {
        this.surplusReserves = surplusReserves;
    }

    public double getTreasuryStock() {
        return treasuryStock;
    }

    public void setTreasuryStock(double treasuryStock) {
        this.treasuryStock = treasuryStock;
    }

    public double getUndistributedProfits() {
        return undistributedProfits;
    }

    public void setUndistributedProfits(double undistributedProfits) {
        this.undistributedProfits = undistributedProfits;
    }

    public double getMinorityShareholdersEquity() {
        return minorityShareholdersEquity;
    }

    public void setMinorityShareholdersEquity(double minorityShareholdersEquity) {
        this.minorityShareholdersEquity = minorityShareholdersEquity;
    }

    public double getForeignCurrencyReportTranslationDifference() {
        return foreignCurrencyReportTranslationDifference;
    }

    public void setForeignCurrencyReportTranslationDifference(double foreignCurrencyReportTranslationDifference) {
        this.foreignCurrencyReportTranslationDifference = foreignCurrencyReportTranslationDifference;
    }

    public double getAbnormalBusinessIncomeAdjustment() {
        return abnormalBusinessIncomeAdjustment;
    }

    public void setAbnormalBusinessIncomeAdjustment(double abnormalBusinessIncomeAdjustment) {
        this.abnormalBusinessIncomeAdjustment = abnormalBusinessIncomeAdjustment;
    }

    public double getOwnersEquity() {
        return ownersEquity;
    }

    public void setOwnersEquity(double ownersEquity) {
        this.ownersEquity = ownersEquity;
    }

    public double getTotalLiabilitiesAndOwnersEquity() {
        return totalLiabilitiesAndOwnersEquity;
    }

    public void setTotalLiabilitiesAndOwnersEquity(double totalLiabilitiesAndOwnersEquity) {
        this.totalLiabilitiesAndOwnersEquity = totalLiabilitiesAndOwnersEquity;
    }
}
