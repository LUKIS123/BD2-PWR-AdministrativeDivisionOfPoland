module AdministrativeDivisionOfPoland.Backend.Contract.main {
    requires com.fasterxml.jackson.annotation;
    requires static lombok;

    exports pl.edu.pwr.contract.Common;
    opens pl.edu.pwr.contract.Common;

    exports pl.edu.pwr.contract.Auth;
    opens pl.edu.pwr.contract.Auth;

    exports pl.edu.pwr.contract.Reports;
    opens pl.edu.pwr.contract.Reports;

    exports pl.edu.pwr.contract.Dtos;
    opens pl.edu.pwr.contract.Dtos;

    exports pl.edu.pwr.contract.OfficeAdres;
    opens pl.edu.pwr.contract.OfficeAdres;

    exports pl.edu.pwr.contract.Voivodeship;
    opens pl.edu.pwr.contract.Voivodeship;

    exports pl.edu.pwr.contract.County;
    opens pl.edu.pwr.contract.County;

    exports pl.edu.pwr.contract.Commune;
    opens pl.edu.pwr.contract.Commune;

    exports pl.edu.pwr.contract.History;
    opens pl.edu.pwr.contract.History;
}